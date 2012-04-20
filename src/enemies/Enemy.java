package enemies;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
 import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
 import character.GameCharacter;
import attributes.Attribute;
import attributes.Updateable;
import enemies.state.EnemyState;

/**
 * 
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class Enemy extends GameCharacter
{
    private ArrayList<Attribute> myAttributes;
    private EnemyState myState;


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


    public void updateAttribute (String name, Object ... o)
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
            if (attribute.getName().equalsIgnoreCase(name) &&
                attribute.getClass().getInterfaces()[0].equals(Updateable.class))
            {
                ((Updateable) attribute).invert();
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

        if (myState != null) myState.excuteBehavior(this);
        else
        {
            for (Attribute attribute : myAttributes)
            {

                if (attribute.getClass().getInterfaces().length != 0 &&
                    attribute.getClass().getInterfaces()[0].equals(Updateable.class))
                {
                    ((Updateable) attribute).update(elapsedTime);
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
        return e;
    }
    
     public boolean equals(Object o){
        try{
            Enemy toCompare = ((Enemy) o);
            return toString().equals(toCompare.toString());
        }
        catch(ClassCastException e){
            return false;
        }
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
        return gson.toJson(paramList);

    }


    public static Enemy fromJson (String json)
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
        Enemy sprite = new Enemy(x, y, imageNames);
        sprite.setGroup(groupName);
        System.out.println("gets here");

        try
        {

        Map<String, String> attributeMap = gson.fromJson(paramList.get(4), collectionType2);
        System.out.println("attribute map: "+attributeMap);
        for(String attributeClassName: attributeMap.keySet())
        {
            
            Class attributeClass;
            
                attributeClass = Class.forName(attributeClassName.substring(6));
            
            String attributeJson = attributeMap.get(attributeClassName);
            Class typeList[] = new Class[1];
            typeList[0] = String.class;
            Method method = attributeClass.getMethod("fromJson", typeList);
            Attribute attribute = (Attribute) method.invoke(null, attributeJson);
            sprite.addAttribute(attribute);

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
