package bonusobjects;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import collisions.CollisionAction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import character.AttributeUser;
import editor.json.AttributeFactory;
import editor.json.JsonableSprite;
import editor.json.SpriteFactory;
import editor.json.SpriteJsonData;


import attributes.*;

@SuppressWarnings("serial")
public class BonusObject extends AttributeUser implements JsonableSprite {

    protected AttributeUser myAttributeUser;
    private List<Attribute> myAttributes;
    private List<Attribute> myAttributesToOffer;
    private static List<AttributeFactory> myAttributeFactories;
    static
    {
        myAttributeFactories = new ArrayList<AttributeFactory>();
        myAttributeFactories.add(Flying.getFactory());
        myAttributeFactories.add(Gravity.getFactory());
        myAttributeFactories.add(Hitpoints.getFactory());
        myAttributeFactories.add(NumberOfLives.getFactory());
        myAttributeFactories.add(NumberOfLives.getFactory());
        myAttributeFactories.add(PointValue.getFactory());
        myAttributeFactories.add(Visibility.getFactory());
        
    }

    public BonusObject(double x, double y, List<String> image)
    {
        super(x, y, image);
        myAttributes = new ArrayList<Attribute>();
        myAttributesToOffer = new ArrayList<Attribute>();
    }

    public List<Attribute> getAttributesToOffer() {
        return Collections.unmodifiableList(myAttributesToOffer);
    }
    
    public void addAttribute(Attribute attributeToAdd) {
        myAttributes.add(attributeToAdd);
    }
    
    public void addAttributeToOffer(Attribute attributeToAdd) {
        myAttributesToOffer.add(attributeToAdd);
    }
    public Class<? extends CollisionAction> getActionClass (){
        return BonusObjectAction.class; 
    }

    
    public void setGameCharacter(AttributeUser gameCharacter)
    {
        myAttributeUser = gameCharacter;
    }

    public Object clone()
    {
        List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        BonusObject c = new BonusObject(this.getX(), this.getY(), imageNames);
        c.setGameCharacter(myAttributeUser);
        for (Attribute a : myAttributes)
        {
            c.addAttribute(a);
        }
        for (Attribute a : myAttributesToOffer)
        {
            c.addAttributeToOffer(a);
        }
        c.setGroup(this.getGroup());
        return c;
    }



    public BonusObject fromJson(String json)
    {
        Gson gson = new Gson();
        SpriteJsonData spriteData = gson.fromJson(json, SpriteJsonData.class);
        BonusObject sprite = new BonusObject(spriteData.getX(), spriteData.getY(), spriteData.getImageNames());
        sprite.setGroup(spriteData.getGroup());
        Type collectionType = new TypeToken<List<String>>() {
        }.getType();
        Type collectionType2 = new TypeToken<Map<String, String>>() {
        }.getType();   
        
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
            /*            Attribute attribute = (Attribute) JsonUtil.getObjectFromJson(
                    attributeClassName, attributeMap.get(attributeClassName));
            sprite.addAttribute(attribute);*/
        }
        Map<String, String> attributeToOfferMap = gson.fromJson(
                paramList.get(1), collectionType2);
        for (String attributeClassName : attributeToOfferMap.keySet())
        {
            for(AttributeFactory factory: myAttributeFactories)
            {
                if(factory.isThisKindOfAttribute(attributeClassName))
                {
                    sprite.addAttributeToOffer(factory.parseFromJson(attributeMap.get(attributeClassName)));
                }
            }
            /*Attribute attribute = (Attribute) JsonUtil.getObjectFromJson(
                    attributeClassName, attributeMap.get(attributeClassName));
            sprite.addAttributeToOffer(attribute);*/
        }
        return sprite;

    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        
        List<String> paramList = new ArrayList<String>();
        Map<String, String> attributeList = new HashMap<String, String>();
        for (Attribute a : myAttributes)
        {
            attributeList.put(a.getClass().toString(), a.toJson());
        }
        paramList.add(gson.toJson(attributeList));
        Map<String, String> attributeToOfferList = new HashMap<String, String>();
        for (Attribute a : myAttributesToOffer)
        {
            attributeToOfferList.put(a.getClass().toString(), a.toJson());
        }
        paramList.add(gson.toJson(attributeToOfferList));
        String additionalInformation = gson.toJson(paramList);
        return gson.toJson(new SpriteJsonData(this, additionalInformation));

    }
    
    
    private BonusObject(){};
    public static SpriteFactory<BonusObject> getFactory()
    {
        return new SpriteFactory<BonusObject>(new BonusObject());
    }

	@Override
	public String getName() {
		return "BonusObject";
	}
    


}
