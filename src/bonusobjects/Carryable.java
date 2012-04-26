package bonusobjects;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import attributes.Attribute;
import editor.json.JsonUtil;
import editor.json.JsonableSprite;
import editor.json.SpriteFactory;
import editor.json.SpriteJsonData;
import fighter.Fighter;

@SuppressWarnings("serial")
public class Carryable extends BonusObject implements JsonableSprite {
	
	protected Fighter		myFighter;
	
	public Carryable(double x, double y,
			List<String> image) {
		super(x, y, image);
	}
	
	public void setFighter(Fighter fighter) {
		myFighter = fighter;
	}
	
	public Object clone()
	{
	    List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
	    Carryable c = new Carryable(this.getX(), this.getY(),imageNames);
	    c.setFighter(myFighter);
	    for(Attribute a: myAttributes)
	    {
	        c.addAttribute(a);
	    }
	    for(Attribute a: myAttributesToOffer)
	    {
	        c.addAttributeToOffer(a);
	    }
	    c.setGroup(this.getGroup());
	    return c;
	}
	

    public  Carryable fromJson(String json)
    {
        Gson gson = new Gson();
        SpriteJsonData spriteData = gson.fromJson(json, SpriteJsonData.class);
        Carryable sprite = new Carryable(spriteData.getX(), spriteData.getY(), spriteData.getImageNames());
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
            Attribute attribute = (Attribute) JsonUtil.getObjectFromJson(
                    attributeClassName, attributeMap.get(attributeClassName));
            sprite.addAttribute(attribute);
        }
        Map<String, String> attributeToOfferMap = gson.fromJson(
                paramList.get(1), collectionType2);
        for (String attributeClassName : attributeToOfferMap.keySet())
        {
            Attribute attribute = (Attribute) JsonUtil.getObjectFromJson(
                    attributeClassName, attributeMap.get(attributeClassName));
            sprite.addAttributeToOffer(attribute);
        }
        return sprite;

    }
    private Carryable(){}
    public static SpriteFactory<Carryable> getFactory()
    {
        return new SpriteFactory<Carryable>(new Carryable());
    }
    


   
}
