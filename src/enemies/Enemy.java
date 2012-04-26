package enemies;

import java.lang.reflect.InvocationTargetException;


import java.lang.reflect.Method;
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
import attributes.*;
import attributes.enemyattributes.Attack;
import attributes.enemyattributes.Flying;
import attributes.enemyattributes.JumpingMovement;
import attributes.enemyattributes.OneDirectionMovement;
import attributes.enemyattributes.PathFollowingMovement;
import attributes.enemyattributes.SideToSideMovement;
import attributes.enemyattributes.UpDownMovement;
import attributes.interfaces.Updateable;
import attributes.sharedattributes.Gravity;
import attributes.sharedattributes.Hitpoints;
import editor.json.AttributeFactory;
import editor.json.JsonableSprite;
import editor.json.SpriteFactory;
import editor.json.SpriteJsonData;
import enemies.state.EnemyState;


/**
 * @author Alex
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class Enemy extends AttributeUser implements JsonableSprite
{
    private ArrayList<Attribute> myAttributes;
    private EnemyState myState;
    private static List<AttributeFactory> myAttributeFactories;
    static
    {
        myAttributeFactories = new ArrayList<AttributeFactory>();
        myAttributeFactories.add(Flying.getFactory());
        myAttributeFactories.add(Hitpoints.getFactory());
        myAttributeFactories.add(Flying.getFactory());
        myAttributeFactories.add(Gravity.getFactory());
        myAttributeFactories.add(SideToSideMovement.getFactory());
        myAttributeFactories.add(UpDownMovement.getFactory());
        myAttributeFactories.add(JumpingMovement.getFactory());
        myAttributeFactories.add(PathFollowingMovement.getFactory());
        myAttributeFactories.add(OneDirectionMovement.getFactory());
    }


    public Enemy (double x, double y, List<String> image)
    {
        super(x, y, image);
        myAttributes = new ArrayList<Attribute>();
        setGroup("ENEMY");
    }


    /**
     * Secret reflection method for sean's uses
     * 
     * @author Alex
     * @param name
     * @return
     */
    public boolean hasAttributeByName (String name)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getClass().getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }


    public void attack (long elapsedTime)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getClass().getSuperclass().equals(Attack.class))
                ((Attack) attribute).update(elapsedTime);
            
                
        }
        
    }


    /**
     * Secret reflection method for sean's uses
     * 
     * @author Alex
     * @param name
     * @return
     */

    public List<Attribute> getAttributes ()
    {
        return Collections.unmodifiableList(myAttributes);
    }


    public void addAttribute (Attribute attribute)
    {
        myAttributes.add(attribute);
        attribute.setGameCharacter(this);
    }


    public void addAttributeList (ArrayList<Attribute> attributes)
    {
        for (Attribute attribute : attributes)
        {
            addAttribute(attribute);
        }
    }


    public void clearAttributes ()
    {
        myAttributes.clear();
    }


    public void removeAttributeByName (String name)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getName().equalsIgnoreCase(name)) myAttributes.remove(attribute);
        }

    }


    private void accessAttributeMethod (String methodStart,
                                        String name,
                                        Object ... o)
    {

        for (Attribute attribute : myAttributes)
        {
            if (attribute.getName().equals(name))
            {
                Class<?> c = attribute.getClass();
                for (Method m : c.getMethods())
                {

                    if (!m.getName().startsWith(methodStart)) continue;
                    if (m.getGenericParameterTypes().length != o.length) continue;
                    for (int i = 0; i < m.getGenericParameterTypes().length; i++)
                    {
                        Class<?> t = m.getParameterTypes()[i];
                        if (!t.equals(o[i]))
                        {

                            continue;
                        }

                    }

                    try
                    {

                        m.invoke(attribute, o);
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

                }
            }
        }

    }


    public void modifyAttribute (String name, Object ... o)
    {
        accessAttributeMethod("modify", name, o);
    }


    public void restoreOriginalAttribute (String name)
    {
        accessAttributeMethod("restore", name);
    }


    public void invertAttribute (String name)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getName().equalsIgnoreCase(name))

            {
                for (Class<?> myInterface : attribute.getClass()
                                                     .getInterfaces())
                {
                    if (myInterface.equals(Updateable.class))
                    {
                        ((Updateable) attribute).invert();
                    }
                }
            }
        }
    }


    public void allowAttribute (String name, boolean activity)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getName().equalsIgnoreCase(name))
            {
                attribute.setActivity(activity);
            }
        }
    }


    public void update (long elapsedTime)
    {

        if (myState != null) myState.excuteBehavior(this, elapsedTime);
        else
        {
            updateUpdateableAttributes(elapsedTime);

        }

    }


    public void updateUpdateableAttributes (long elapsedTime)
    {
        for (Attribute attribute : myAttributes)
        {

            if (attribute.getClass().getInterfaces().length != 0)
            {
                for (Class<?> myInterface : attribute.getClass()
                                                     .getInterfaces())
                {
                    if (myInterface.equals(Updateable.class))
                    {
                        ((Updateable) attribute).update(elapsedTime);
                    }
                }
            }
        }
    }


    public void setState (EnemyState state)
    {
        myState = state;
    }

    public String getName ()
    {
        return "Enemy";
    }


    public String toString ()
    {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(super.toString());
        if (myState != null)
        {
            toReturn.append(myState.toString());
        }
        return toReturn.toString();
    }


    public Object clone ()
    {
        List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        Enemy e = new Enemy(this.getX(), this.getY(), imageNames);
        for (Attribute a : myAttributes)
        {
            e.addAttribute((Attribute) a.clone());
        }
        if (myState != null)
        {
            e.setState(myState);
        }
        e.setGroup(this.getGroup());
        return e;
    }


//    public boolean equals (Object o)
//    {
//        try
//        {
//
//            Enemy toCompare = ((Enemy) o);
//            if(!toString().equals(toCompare.toString()))
//                    return false;
//            for(String im: toCompare.getImageNames())
//            {
//                if(!getImageNames().contains(im))
//                {
//                    return false;
//                }
//            }
//        }
//        catch (ClassCastException e)
//        {
//            return false;
//        }
//        return false;
//    }


    public String toJson ()
    {
        Gson gson = new Gson();
        Map<String, String> attributeList = new HashMap<String, String>();
        for (Attribute a : myAttributes)
        {
            attributeList.put(a.getClass().toString(), a.toJson());
        }
        String additionalInformation = gson.toJson(attributeList);
        
        return gson.toJson(new SpriteJsonData(this, additionalInformation));

    }
    
    
    public Class<? extends CollisionAction> getActionClass (){
    	return EnemyAction.class; 
    }


    public  Enemy fromJson (String json)
    {
        Gson gson = new Gson();
        
        SpriteJsonData spriteData = gson.fromJson(json, SpriteJsonData.class);
        Enemy sprite = new Enemy(spriteData.getX(), spriteData.getY(), spriteData.getImageNames());
        sprite.setGroup(spriteData.getGroup());
        Type collectionType = new TypeToken<HashMap<String, String>>()
        {}.getType();
        Map<String, String> attributeMap = gson.fromJson(spriteData.getAdditionalInformation(), collectionType);
        System.out.println("attribute map: " + attributeMap);
        for (String attributeClassName : attributeMap.keySet())
        {
                for(AttributeFactory factory: myAttributeFactories)
                {
                    if(factory.isThisKindOfAttribute(attributeClassName))
                    {
                        sprite.addAttribute(factory.parseFromJson(attributeMap.get(attributeClassName)));
                    }
                }
                /*Attribute attribute = (Attribute) JsonUtil.getObjectFromJson(attributeClassName, attributeMap.get(attributeClassName));
                sprite.addAttribute(attribute);*/

        }
        return sprite;
    }
    
    private Enemy(){}
    
    public static SpriteFactory<Enemy> getFactory()
    {
        return new SpriteFactory<Enemy>( new Enemy());
    }


    
}
