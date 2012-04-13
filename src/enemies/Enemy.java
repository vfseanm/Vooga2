package enemies;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import attributes.Attribute;
import attributes.Updateable;
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

    /**
     * Secret relfection method for sean's uses
     * @author Alex
     * @param name
     * @return
     */
    public boolean hasAttributeByName(String name)
    {
        for(Attribute attribute: myAttributes)
        {
            if(attribute.getClass().getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    
    /**
     * Secret relfection method for sean's uses
     * @author Alex
     * @param name
     * @return
     */

    public List<Attribute> getAttributes()
    {
        return Collections.unmodifiableList(myAttributes);
    }
    
    public void addAttribute (Attribute attribute)
    {
        myAttributes.add(attribute);
        attribute.setEnemy(this);
    }
    
    public void clearAttributes(){
        myAttributes.clear();
    }


    public void removeAttributeByName (String name)
    {
        for(Attribute attribute: myAttributes)
        {
            if(attribute.getName().equalsIgnoreCase(name));
                myAttributes.remove(attribute);
        }
        
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


    public void update (long elapsedTime)
    {

        for (Attribute attribute : myAttributes)
        {

            if (attribute.getClass().getInterfaces().length != 0 &&
                attribute.getClass().getInterfaces()[0].equals(Updateable.class))
            {
                try
                {
                    //necessary?

                    ((Updateable) attribute).update(elapsedTime);
                }
                catch (ClassCastException e)
                {

                    e.printStackTrace();
                }
            }
        }
        if (myState != null) myState.excuteBehavior(this);
    }


    public void setState (EnemyState state)
    {
        myState = state;
    }


    public String toString ()
    {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Enemy\n");
        for (Attribute attribute : myAttributes)
        {
            toReturn.append(attribute.toString());
            toReturn.append("\n");
        }
        if(myState!=null)
        {
            toReturn.append(myState.toString());
        }
        return toReturn.toString();
    }
}
