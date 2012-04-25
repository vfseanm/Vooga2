package bonusobjects;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import character.GameCharacter;
import editor.ReflectionUtil;
import editor.json.JsonUtil;
import editor.json.Jsonable;
import editor.json.SpriteJsonData;

import attributes.*;

@SuppressWarnings("serial")
public class PowerUp extends BonusObject implements Jsonable {

    protected GameCharacter myGameCharacter;

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



    public static PowerUp fromJson(String json)
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
    


}
