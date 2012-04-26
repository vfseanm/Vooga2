package bonusobjects;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import character.GameCharacter;
import editor.json.AttributeFactory;
import editor.json.JsonUtil;
import editor.json.Jsonable;
import editor.json.SpriteFactory;
import editor.json.SpriteJsonData;


import attributes.*;

@SuppressWarnings("serial")
public class PowerUp extends BonusObject implements Jsonable {

    protected GameCharacter myGameCharacter;
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

    public PowerUp(double x, double y, List<String> image)
    {
        super(x, y, image);
    }

    public void setGameCharacter(GameCharacter gameCharacter)
    {
        myGameCharacter = gameCharacter;
    }

    public Object clone()
    {
        List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        PowerUp c = new PowerUp(this.getX(), this.getY(), imageNames);
        c.setGameCharacter(myGameCharacter);
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



    public PowerUp fromJson(String json)
    {
        Gson gson = new Gson();
        SpriteJsonData spriteData = gson.fromJson(json, SpriteJsonData.class);
        PowerUp sprite = new PowerUp(spriteData.getX(), spriteData.getY(), spriteData.getImageNames());
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
                if(factory.isThisKindOfSprite(attributeClassName))
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
                if(factory.isThisKindOfSprite(attributeClassName))
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
    
    private PowerUp(){};
    public static SpriteFactory<PowerUp> getFactory()
    {
        return new SpriteFactory<PowerUp>(new PowerUp());
    }
    


}
