package fighter;

import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platforms.platformtypes.SideToSidePlatform;

import collisions.CollisionAction;

import com.golden.gamedev.engine.BaseInput;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import character.GameCharacter;
import editor.ReflectionUtil;
import editor.json.AttributeFactory;
import editor.json.JsonUtil;
import editor.json.JsonableSprite;
import editor.json.SpriteFactory;
import editor.json.SpriteJsonData;
import attributes.Attribute;
import attributes.Flying;
import attributes.Gravity;
import attributes.Hitpoints;
import attributes.NumberOfLives;
import attributes.PointValue;
import attributes.Visibility;
import attributes.fighterattributes.BasicMovement;
import attributes.fighterattributes.Fly;
import attributes.fighterattributes.Input;
import attributes.fighterattributes.Jump;
import attributes.fighterattributes.Movement;


@SuppressWarnings("serial")
public class Fighter extends GameCharacter implements JsonableSprite  {

    private List<Attribute> myCarryableAttributes;
    private List<Attribute> myDuplicateAttributes;
    private BaseInput myUserInput;
    private static Fighter myself;
    private static List<AttributeFactory> myAttributeFactories;
    static
    {
        myAttributeFactories = new ArrayList<AttributeFactory>();
        myAttributeFactories.add(BasicMovement.getFactory());
        myAttributeFactories.add(Fly.getFactory());
        myAttributeFactories.add(Jump.getFactory());
        myAttributeFactories.add(Flying.getFactory());
        myAttributeFactories.add(Gravity.getFactory());
        myAttributeFactories.add(Hitpoints.getFactory());
        myAttributeFactories.add(NumberOfLives.getFactory());
        myAttributeFactories.add(NumberOfLives.getFactory());
        myAttributeFactories.add(PointValue.getFactory());
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

        if (myUserInput.isKeyDown(KeyEvent.VK_C))
        {
            // POP UP DIALOG ALLOWING YOU TO CHOOSE CARRYABLE OBJECT? CAN WE
            // ACTUALLY PAUSE THE GAME?
            // OTHERWISE, SIMPLY HAVE KEYSTROKES = INDEX OF CARRYABLE ITEMS IN
            // LIST? MAX = 6?
        }
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
        if (toAdd.getClass().getInterfaces().length >= 3)
        {
            if (toAdd.getClass().getInterfaces()[2].equals(Input.class))
                ((Input) toAdd).setUserInput(myUserInput);
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
                    carryableAttributes.add(factory.parseFromJson(attributeMap.get(attributeClassName)));
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
