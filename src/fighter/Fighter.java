package fighter;

import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import collisions.CollisionAction;

import com.golden.gamedev.engine.BaseInput;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import character.AttributeUser;
import editor.json.AttributeFactory;
import editor.json.JsonableSprite;
import editor.json.SpriteFactory;
import editor.json.SpriteJsonData;
import attributes.Attribute;
import attributes.enemyattributes.Flying;
import attributes.fighterattributes.FighterBasicMovement;
import attributes.fighterattributes.FighterFly;
import attributes.fighterattributes.FighterJump;
import attributes.fighterattributes.FighterScore;
import attributes.interfaces.Input;
import attributes.interfaces.Movement;
import attributes.sharedattributes.Gravity;
import attributes.sharedattributes.Hitpoints;
import attributes.sharedattributes.NumberOfLives;
import attributes.sharedattributes.Visibility;


@SuppressWarnings("serial")
public class Fighter extends AttributeUser implements JsonableSprite  {
	
	transient protected ResourceBundle myGameKeys = ResourceBundle
    .getBundle("demo.GameKeysResourceBundle");

    private List<Attribute> myCarryableAttributes;
    private List<Attribute> myDuplicateAttributes;
    private BaseInput myUserInput;
    private static Fighter myself;
    private static List<AttributeFactory> myAttributeFactories;
    
    static
    {
        myAttributeFactories = new ArrayList<AttributeFactory>();
        myAttributeFactories.add(FighterBasicMovement.getFactory());
        myAttributeFactories.add(FighterFly.getFactory());
        myAttributeFactories.add(FighterJump.getFactory());
        myAttributeFactories.add(Flying.getFactory());
        myAttributeFactories.add(Gravity.getFactory());
        myAttributeFactories.add(Hitpoints.getFactory());
        myAttributeFactories.add(NumberOfLives.getFactory());
        myAttributeFactories.add(NumberOfLives.getFactory());
        myAttributeFactories.add(FighterScore.getFactory());
        myAttributeFactories.add(Visibility.getFactory());  
    }

    // public void render(Graphics2D pen){
    // myself.render(pen);
    // }
    // public void update()

    // public Fighter(double x, double y, List<String> images) {
    // super(x, y, images);
    // myCarryableAttributes = new ArrayList<Attribute>();
    // setGroup("FIGHTER");
    // }
    
    private Fighter()
    {
        super();
        setGroup("FIGHTER");
    };
    
    public String getGroup(){
        return ("FIGHTER");
    }

    public static Fighter getInstance()
    {
        
        if (myself == null)
        {
            myself = new Fighter();
            myself.myAttributes = new ArrayList<Attribute>();
            myself.myCarryableAttributes = new ArrayList<Attribute>();
            myself.myDuplicateAttributes = new ArrayList<Attribute>();
        }
        return myself;
    }

    public void update(long elapsedTime)
    {
        performAttributeActions(elapsedTime);
    }
    
    

    /**
     * Adds Attributes, removing older, duplicate versions; also sets user input
     * for the Fighter's Movement Attributes, enabling use of key strokes.
     */
    public void addAttribute(Attribute toAdd)
    {
        Attribute currentVersion = getAttributeByName(toAdd.getName());
        if (currentVersion != null)
        {
            myAttributes.remove(currentVersion);
        }
        super.addAttribute(toAdd);
        
        Class[] attributeInterfaces = toAdd.getClass().getInterfaces();
    	if (Arrays.asList(attributeInterfaces).contains(Input.class)) {
    		Input inputAttribute = (Input) toAdd;
    		inputAttribute.setUserInput(myUserInput);
    	}
    }
    

    public void addCarryableAttributes(List<Attribute> carryables)
    {
        myCarryableAttributes.addAll(carryables);
    }

    
    public void useCarryableAttribute(int indexCarryableAttribute)
    {
        try
        {
            myAttributes
                    .add(myCarryableAttributes.get(indexCarryableAttribute));
            myCarryableAttributes.remove(indexCarryableAttribute);
        } catch (IndexOutOfBoundsException e)
        {
            System.out
                    .println("This Carryable Attribute is not in your inventory.");
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
	
	public List<Attribute> getCarryableAttributes()
	{
	    return myCarryableAttributes;
	}
	
	
	public void setUserInput(BaseInput userInput) {
		myUserInput = userInput;
		System.out.println("seting inpput");
	
		for (Attribute ability: getAttributes()) {
        	Class[] attributeInterfaces = ability.getClass().getInterfaces();
        	if (Arrays.asList(attributeInterfaces).contains(Input.class)) {
        		Input inputAttribute = (Input) ability;
        		inputAttribute.setUserInput(userInput);
        	}
        }
	}
	
	public BaseInput getUserInput() {
		return myUserInput;
	}
	
	public String getName() {
		return "Fighter";
	}
	

    public Class<? extends CollisionAction> getActionClass (){
    	return FighterAction.class; 
    }



    public String toJson()
    {
        Gson gson = new Gson();
        List<String> paramList = new ArrayList<String>();
        Map<String, String> attributeMap = new HashMap<String, String>();
        for (Attribute a : myAttributes)
        {
            attributeMap.put(a.getClass().toString(), a.toJson());
        }
        paramList.add(gson.toJson(attributeMap));
        Map<String, String> carryableAttributeMap = new HashMap<String, String>();
        for (Attribute a : myCarryableAttributes)
        {
            carryableAttributeMap.put(a.getClass().toString(), a.toJson());
        }
        paramList.add(gson.toJson(carryableAttributeMap));
        String additionalInformation = gson.toJson(paramList); 
        return gson.toJson(new SpriteJsonData(this, additionalInformation));
    }

    public  Fighter fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>() {
        }.getType();
        Type collectionType2 = new TypeToken<Map<String, String>>() {
        }.getType();
        SpriteJsonData spriteData = gson.fromJson(json, SpriteJsonData.class);
        Fighter sprite = Fighter.getInstance();
        sprite.setLocation(spriteData.getX(), spriteData.getY());
        sprite.setImageNamesandImages(spriteData.getImageNames());
        sprite.setGroup(spriteData.getGroup());
        List<String> paramList = gson.fromJson(spriteData.getAdditionalInformation(), collectionType);
        Map<String, String> attributeMap = gson.fromJson(paramList.get(0),
                collectionType2);
        for (String attributeClassName : attributeMap.keySet())
        {

            for(AttributeFactory factory: myAttributeFactories)
            {
                if(factory.isThisKindOfAttribute(attributeClassName))
                {
                    sprite.addAttribute(factory.parseFromJson(attributeMap.get(attributeClassName)));
                }
            }
            /*sprite.addAttribute((Attribute) JsonUtil.getObjectFromJson(
                    attributeClassName, attributeMap.get(attributeClassName)));
*/
        }
        List<Attribute> carryableAttributes = new ArrayList<Attribute>();
        Map<String, String> carryableAttributeMap = gson.fromJson(
                paramList.get(1), collectionType2);
        for (String attributeClassName : carryableAttributeMap.keySet())
        {
            for(AttributeFactory factory: myAttributeFactories)
            {
                if(factory.isThisKindOfAttribute(attributeClassName))
                {
                    carryableAttributes.add(factory.parseFromJson(carryableAttributeMap.get(attributeClassName)));
                }
            }
            /*carryableAttributes.add((Attribute) JsonUtil.getObjectFromJson(
                    attributeClassName, attributeMap.get(attributeClassName)));*/

        }
        sprite.addCarryableAttributes(carryableAttributes);
        return sprite;
    }
    
    
    public static SpriteFactory getFactory()
    {
        return new SpriteFactory(new Fighter());
    }

}
