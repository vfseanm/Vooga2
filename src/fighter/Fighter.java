package fighter;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.golden.gamedev.engine.BaseInput;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import character.GameCharacter;
import enemies.Enemy;
import fighter.movement.Input;
import fighter.movement.Movement;
import attributes.Attribute;


@SuppressWarnings("serial")
public class Fighter extends GameCharacter {

	private List<Attribute>					myCarryableAttributes;
	private BaseInput						myUserInput;
	private static Fighter myself;
	
//	public void render(Graphics2D pen){
//	    myself.render(pen);
//	}
	//public void update()
	
//	public Fighter(double x, double y, List<String> images) {
//		super(x, y, images);
//		myCarryableAttributes = new ArrayList<Attribute>();
//		setGroup("FIGHTER");
//	}
	private Fighter(){
	    super();
	};
	
	public static Fighter getInstance()
	{
	    
	    if(myself==null)
	    {
	        myself = new Fighter();
	        myself.myAttributes = new ArrayList<Attribute>();
	        myself.myCarryableAttributes = new ArrayList<Attribute>();
	    }
	    
	    return myself;
	}
	
    public void update(long elapsedTime) {
		performAttributeActions(elapsedTime);
		
		if (myUserInput.isKeyDown(KeyEvent.VK_C)) 
		{
		    // POP UP DIALOG ALLOWING YOU TO CHOOSE CARRYABLE OBJECT? CAN WE ACTUALLY PAUSE THE GAME?
			// OTHERWISE, SIMPLY HAVE KEYSTROKES = INDEX OF CARRYABLE ITEMS IN LIST? MAX = 6?
		}
	}
    
    
    /**
	 * Adds Attributes, removing older, duplicate versions; also sets user input for
	 * the Fighter's Movement Attributes, enabling use of key strokes.                    
	 */
    public void addAttribute(Attribute toAdd) {	
    	Attribute currentVersion = getAttributeByName(toAdd.getName());
    	if (currentVersion != null) {
    		myAttributes.remove(currentVersion);
    	}
    	super.addAttribute(toAdd);
    	if (toAdd.getClass().getInterfaces().length >= 3) {
               if (toAdd.getClass().getInterfaces()[2].equals(Input.class))
            	   ((Input) toAdd).setUserInput(myUserInput);
        }
	}
    
 
    
    public void addCarryableAttributes(List<Attribute> carryables) {
    	myCarryableAttributes.addAll(carryables);
    	
    	// method for adding attributes to inventory of limited length = myMaxNumCarryables
/*    	for (int i = myCarryableAttributes.size(); i < myMaxNumCarryables; i++) {
    		myCarryableAttributes.add(i, carryables.get(i-myCarryableAttributes.size()));
    	}*/
    }
    
    
    
    public void useCarryableAttribute(int indexCarryableAttribute)  {
    	try {
    		myAttributes.add(myCarryableAttributes.get(indexCarryableAttribute));
    		myCarryableAttributes.remove(indexCarryableAttribute);
    	}
    	catch (IndexOutOfBoundsException e) {
    		System.out.println("This Carryable Attribute is not in your inventory.");
    	}
    }
	
	
	
	/**
	 * Method for getting horizontal and vertical movement distances                           
	 * to ensure accurate side scrolling.
	 *         
	 * @return array with [0] = horizontal movement & [1] = vertical movement
	 */
	public double[] getMovement() {
		double[] horizVertMovement = new double[2];
		for (Attribute attribute : myAttributes) {
			if (attribute.getClass().getInterfaces().length >= 2) {
	            if (attribute.getClass().getInterfaces()[1].equals(Movement.class)) {
	            	Movement scrollSpeed = (Movement) attribute;
	            	horizVertMovement[0] += scrollSpeed.getHorizMovement();
	            	horizVertMovement[1] += scrollSpeed.getVertMovement();
	            }
			}
		}
		return horizVertMovement;
	}
	
	
	public void setUserInput(BaseInput userInput) {
		myUserInput = userInput;
	}
	
	public BaseInput getUserInput() {
		return myUserInput;
	}
	
	public String getName() {
		return "Fighter";
	}
    public String toJson ()
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>()
        {}.getType();
        List<String> paramList = new ArrayList<String>();
        paramList.add(gson.toJson(this.getImageNames()));
        paramList.add(this.getGroup());
        paramList.add(this.getX() + "");
        paramList.add(this.getY() + "");

        Map<String, String> attributeMap = new HashMap<String, String>();
        for (Attribute a : myAttributes)
        {
            System.out.println("adding attribute to json");
            attributeMap.put(a.getClass().toString(), a.toJson());
        }
        paramList.add(gson.toJson(attributeMap));
        Map<String, String> carryableAttributeMap = new HashMap<String, String>();
        for(Attribute a : myCarryableAttributes)
        {
            carryableAttributeMap.put(a.getClass().toString(), a.toJson());
        }
        paramList.add(gson.toJson(carryableAttributeMap));
        return gson.toJson(paramList);

    }


    public static Fighter fromJson (String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>()
        {}.getType();
        Type collectionType2 = new TypeToken<Map<String, String>>()
        {}.getType();

        List<String> paramList = gson.fromJson(json, collectionType);
        List<String> imageNames =
            gson.fromJson(paramList.get(0), collectionType);
        String groupName = paramList.get(1);
        double x = Double.parseDouble(paramList.get(2));
        double y = Double.parseDouble(paramList.get(3));
        Fighter sprite = Fighter.getInstance();
        sprite.setLocation(x, y);
        sprite.setImageNames(imageNames);
        sprite.setGroup(groupName);
        System.out.println("gets here");

        try
        {

            Map<String, String> attributeMap =
                gson.fromJson(paramList.get(4), collectionType2);
            System.out.println("attribute map: " + attributeMap);
            for (String attributeClassName : attributeMap.keySet())
            {

                Class attributeClass;

                attributeClass = Class.forName(attributeClassName.substring(6));

                String attributeJson = attributeMap.get(attributeClassName);
                Class typeList[] = new Class[1];
                typeList[0] = String.class;
                Method method = attributeClass.getMethod("fromJson", typeList);
                Attribute attribute =
                    (Attribute) method.invoke(null, attributeJson);
                sprite.addAttribute(attribute);

            }
            List<Attribute> carryableAttributes = new ArrayList<Attribute>();
            Map<String, String> carryableAttributeMap =
                gson.fromJson(paramList.get(5), collectionType2);
            for (String attributeClassName : carryableAttributeMap.keySet())
            {

                Class attributeClass;

                attributeClass = Class.forName(attributeClassName.substring(6));

                String attributeJson = carryableAttributeMap.get(attributeClassName);
                Class typeList[] = new Class[1];
                typeList[0] = String.class;
                System.out.println(attributeJson);
                Method method = attributeClass.getMethod("fromJson", typeList);
                
                Attribute attribute =
                    (Attribute) method.invoke(null, attributeJson);
                carryableAttributes.add(attribute);

            }
            sprite.addCarryableAttributes(carryableAttributes);
            return sprite;
        }

        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return null;

    }
	
}
