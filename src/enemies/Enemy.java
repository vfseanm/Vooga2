package enemies;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sprite.AnimatedGameSprite;

import character.GameCharacter;
import attributes.Attribute;
import attributes.Updateable;
import enemies.state.EnemyState;


@SuppressWarnings("serial")
public class Enemy extends GameCharacter
{
    private ArrayList<Attribute> myAttributes;
    private EnemyState myState;


    public Enemy (BufferedImage[] im, double x, double y, List<String> image)
    {
        super(im, x, y, image);
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
        for (Attribute attribute : myAttributes) {
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
    
    public void addAttributeList(ArrayList<Attribute> attributes){
        for(Attribute attribute: attributes){
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
            if (attribute.getName().equalsIgnoreCase(name))
            myAttributes.remove(attribute);
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
        
        for (Attribute attribute : myAttributes)
        {
            
            if (attribute.getClass().getInterfaces().length != 0 &&
                attribute.getClass().getInterfaces()[0].equals(Updateable.class))
            {
                ((Updateable) attribute).update(elapsedTime);
            }
        }
        if (myState != null) myState.excuteBehavior(this);
    }


    public void setState (EnemyState state)
    {
        myState = state;
    }

    public String getName() {
    	return "Enemy";
    }
    
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(nameAndAttributesToString());
        if (myState != null)
        {
            toReturn.append(myState.toString());
        }
        return toReturn.toString();
    }
}
