package bonusobjects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import character.GameCharacter;
import enemies.Enemy;
import attributes.*;

@SuppressWarnings("serial")
public class PowerUp extends BonusObject {
	
	protected GameCharacter			myGameCharacter;
	
	public PowerUp(double x, double y, List<String> image) {
		super(x, y, image);
	}
	
	public void setGameCharacter(GameCharacter gameCharacter) {
		myGameCharacter = gameCharacter;
	}
	
   public Object clone()
    {
       List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        PowerUp c = new PowerUp(this.getX(), this.getY(),imageNames);
        c.setGameCharacter(myGameCharacter);
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
       
       return gson.toJson(paramList);

   }


   public static PowerUp fromJson (String json)
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
       PowerUp sprite = new PowerUp(x, y, imageNames);
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
           Map<String, String> attributeToOfferMap =
               gson.fromJson(paramList.get(5), collectionType2);
           System.out.println(" carryable attribute map: " + attributeToOfferMap);
           for (String attributeClassName : attributeToOfferMap.keySet())
           {

               Class attributeClass;

               attributeClass = Class.forName(attributeClassName.substring(6));

               String attributeJson = attributeMap.get(attributeClassName);
               Class typeList[] = new Class[1];
               typeList[0] = String.class;
               Method method = attributeClass.getMethod("fromJson", typeList);
               Attribute attribute =
                   (Attribute) method.invoke(null, attributeJson);
               sprite.addAttributeToOffer(attribute);

           }
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
