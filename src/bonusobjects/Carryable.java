package bonusobjects;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import attributes.Attribute;
import attributes.Flying;
import editor.Reflection;
import editor.file.Jsonable;
import fighter.Fighter;

@SuppressWarnings("serial")
public class Carryable extends BonusObject implements Jsonable {
	
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
	

    public static Carryable fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>() {
        }.getType();
        Type collectionType2 = new TypeToken<Map<String, String>>() {
        }.getType();

        List<String> paramList = gson.fromJson(json, collectionType);
        List<String> imageNames = gson.fromJson(paramList.get(0),
                collectionType);
        String groupName = paramList.get(1);
        double x = Double.parseDouble(paramList.get(2));
        double y = Double.parseDouble(paramList.get(3));
        Carryable sprite = new Carryable(x, y, imageNames);
        sprite.setGroup(groupName);
        Map<String, String> attributeMap = gson.fromJson(paramList.get(4),
                collectionType2);
        for (String attributeClassName : attributeMap.keySet())
        {
            Attribute attribute = (Attribute) Reflection.getObjectFromJson(
                    attributeClassName, attributeMap.get(attributeClassName));
            sprite.addAttribute(attribute);
        }
        Map<String, String> attributeToOfferMap = gson.fromJson(
                paramList.get(5), collectionType2);
        for (String attributeClassName : attributeToOfferMap.keySet())
        {
            Attribute attribute = (Attribute) Reflection.getObjectFromJson(
                    attributeClassName, attributeMap.get(attributeClassName));
            sprite.addAttributeToOffer(attribute);
        }
        return sprite;

    }
    
/*    private Carryable(){}
    
    public static ObjectFromJsonFactory getFactory()
    {
        return new ObjectFromJsonFactory(new Carryable());
    }
   */
    

   
}
