package fighter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bonusobjects.BonusObject;
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


@SuppressWarnings({ "serial", "rawtypes" })
public class Fighter extends AttributeUser implements JsonableSprite  {

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
        myAttributeFactories.add(FighterScore.getFactory());
        myAttributeFactories.add(Visibility.getFactory());  
    }

    
    private Fighter()
    {
        super();
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
            myself.setGroup("FIGHTER");
        }
        return myself;
    }
    
    public Attribute getAttributeByName(String name) {
        for (Attribute attribute : myAttributes) {
            if (attribute.getClass().getName().equalsIgnoreCase(name))
                return attribute;
        }
        return null;
    }

    


    
    public void addPowerUp(BonusObject bonus) {
        for (Attribute toAdd: bonus.getAttributesToOffer()) {
            addAttribute(toAdd);
        }
    }

    
    

    /**
     * Adds unique Attributes to myAttributes and duplicates to myDuplicateAttributes, 
     * where they will be held, inactive, till later use. 
     * Sets user input for the Fighter's Movement Attributes, enabling use of key strokes
     * to the Fighter.
     */
    public void addAttribute(Attribute toAdd)
    {
        if (hasAttributeByName(toAdd.getName()))
        {
            myDuplicateAttributes.add(toAdd);
        }
        else super.addAttribute(toAdd);
        
        Class[] attributeInterfaces = toAdd.getClass().getInterfaces();
    	if (Arrays.asList(attributeInterfaces).contains(Input.class)) {
    		Input inputAttribute = (Input) toAdd;
    		inputAttribute.setUserInput(myUserInput);
    	}
    }
    
    
    
    /**
     * Adds unique Attributes to myAttributes and duplicates to myDuplicateAttributes, 
     * where they will be held, inactive, till later use. 
     * Sets user input for the Fighter's Movement Attributes, enabling use of key strokes
     * to control the Fighter.
     */
    public void removeAttributeByName(String name) {
		for (Attribute attribute : myAttributes) {
			if (attribute.getName().equalsIgnoreCase(name))
				myAttributes.remove(attribute);
		}
		
		for (Attribute attribute : myDuplicateAttributes) {
			if (attribute.getName().equalsIgnoreCase(name))
			{
				myAttributes.add(attribute);
				myDuplicateAttributes.remove(attribute);		
			}
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
	
    
    public List<Attribute> getCarryableAttributes()
	{
	    return Collections.unmodifiableList(myCarryableAttributes);
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
			Class[] attributeInterfaces = attribute.getClass().getInterfaces();
        	if (Arrays.asList(attributeInterfaces).contains(Movement.class)) {
        		Movement scrollSpeed = (Movement) attribute;
        		horizVertMovement[0] += scrollSpeed.getHorizMovement();
            	horizVertMovement[1] += scrollSpeed.getVertMovement();
        	}
		}
		return horizVertMovement;
	}
	
	
	 /**
     * Sets user input for the Fighter and all the Movement Attributes it starts
     * with, enabling use of key strokes to control the Fighter.
     */
	public void setUserInput(BaseInput userInput) {
		myUserInput = userInput;
	
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
        }
        sprite.addCarryableAttributes(carryableAttributes);
        return sprite;
    }
    
    
    @SuppressWarnings("unchecked")
	public static SpriteFactory getFactory()
    {
        return new SpriteFactory(new Fighter());
    }

}
