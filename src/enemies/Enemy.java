package enemies;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import enemies.attributes.Attribute;
import enemies.attributes.Updateable;
import enemies.state.EnemyState;
import sprite.AnimatedGameSprite;


@SuppressWarnings("serial")
public class Enemy extends AnimatedGameSprite 
{
    private ArrayList<Attribute> myAttributes;
    private EnemyState myState;


    public Enemy (BufferedImage[] im, double x, double y, List<String> image)
    {
        super(im, x, y, image);
        myAttributes = new ArrayList<Attribute>();
    }


    public void addAttribute (Attribute attribute)
    {
        myAttributes.add(attribute);
        attribute.setEnemy(this);
    }
    
    public void removeAttribute (Attribute attribute){
        if(myAttributes.contains(attribute))
            myAttributes.remove(attribute);
    }


    public void updateAttribute (String name, Object ... o)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getName().equals(name))
            {
                Class<?> c = attribute.getClass();
                for (Method m : c.getMethods())
                {
                    if (!m.getName().startsWith("modify")) continue;
                    if (m.getGenericParameterTypes().length != o.length) continue;
                    for (int i = 0; i < m.getGenericParameterTypes().length; i++)
                    {
                        Class<?> t = m.getParameterTypes()[i];
                        if (!t.isInstance(o[i]))
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


    public void update (long elapsedTime)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getClass().isInterface())
            {
                try
                {
                    ((Updateable) attribute).update(elapsedTime);
                }
                catch (ClassCastException e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(myState!=null)myState.excuteBehavior(this);
    }


    public void setState (EnemyState state)
    {
        myState = state;
    }


    public String toString ()
    {
        StringBuilder toReturn = new StringBuilder();
        for (Attribute attribute : myAttributes)
        {
            toReturn.append(attribute.toString());
            toReturn.append("\n");
        }
        toReturn.append(myState.toString());
        return toReturn.toString();
    }
}
