package character;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sprite.AnimatedGameSprite;
import attributes.Attribute;
import attributes.interfaces.Updateable;


/**
 * Abstract super class for enemy, fighter and bonus objects. encapsulates
 * attribute handling that is basic to all three of these classes
 * 
 * @author Alex
 */
@SuppressWarnings("serial")
public abstract class AttributeUser extends AnimatedGameSprite
{

    protected List<Attribute> myAttributes;


    /**
     * @param x x coordinate of the sprite
     * @param y y coordinate of the sprite
     * @param images list of images associated with the character
     */
    public AttributeUser (double x, double y, List<String> images)
    {
        super(x, y, images);
        myAttributes = new ArrayList<Attribute>();
    }


    protected AttributeUser ()
    {
        myAttributes = new ArrayList<Attribute>();
    };


    /**
     * @author Alex
     * @param name The name of the attribute that you are looking for
     * @return true if the name is contained within the list of attributes
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
     * Used by the level editor team
     * 
     * @return an unmodifiablelist of myAttributes
     */
    public List<Attribute> getAttributes ()
    {
        return Collections.unmodifiableList(myAttributes);
    }


    /**
     * Adds an attribute to the list of attributes Also associates the attribute
     * with the type of attribute user (enemy, fighter, or bonus object)
     * 
     * @param attribute attribute to add to the list
     */
    public void addAttribute (Attribute attribute)
    {
        myAttributes.add(attribute);
        attribute.setAttributeUser(this);
    }


    /**
     * Adds a list of attributes to the attribute user's currentlist calls the
     * add attribute method for each attribute
     * 
     * @param attributes
     */
    public void addAttributeList (ArrayList<Attribute> attributes)
    {
        for (Attribute attribute : attributes)
        {
            addAttribute(attribute);
        }
    }


    /**
     * removes all attributes from myAttributes
     */
    public void clearAttributes ()
    {
        myAttributes.clear();
    }


    /**
     * Removes an attribute by name from the list of attributes
     * 
     * @param name the name of the Attribute to remove
     */
    public void removeAttributeByName (String name)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getName().equalsIgnoreCase(name)) myAttributes.remove(attribute);
        }

    }


    /**
     * helper method which is used by several other methods
     * 
     * @param methodStart beginning string of the attribute method which is
     *            going to be called
     * @param name the name of the attribute whose method is to be called
     * @param o the objects which are the necessary parameters to call the
     *            method in the attribute
     */
    protected void accessAttributeMethod (String methodStart,
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


    /**
     * Used for attribute encapsulation this method is used to call the modify
     * method in a given attribute and takes the necessary parameters to modify
     * this attribute
     * 
     * @param name name of the attribute to call the modify method on
     * @param o the parameters necessary to call the modify method
     */
    public void modifyAttribute (String name, Object ... o)
    {
        accessAttributeMethod("modify", name, o);
    }


    /**
     * calls the helper method accessAttributeMethod reverts a given attribute
     * to the original state (the one which it was constructor with)
     * 
     * @param name name of the attribute
     */
    public void restoreOriginalAttribute (String name)
    {
        accessAttributeMethod("restore", name);
    }


    /**
     * calls the helper method accessAttributeMethod to invert attributes the
     * attribute you try to invert needs to be an updateable attribute and it
     * will call the invert method on that attribute
     * 
     * @param name name of the attribute that you want to be inverted
     */
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


    /**
     * Finds the given attribute by name and then sets the activity to the given
     * value
     * 
     * @param name the name of the attribute which you are trying to set the
     *            activity of
     * @param activity boolean variable which will be used to set the isActive
     *            of the attribute
     */
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


    /**
     * Calls the performUpdateableAttributes Is called by the GTGE update call
     * 
     * @param elapsedTime the time change during each update call
     */
    public void update (long elapsedTime)
    {

        performUpdateableAttributes(elapsedTime);

    }


    /**
     * calls the update method of all the attributes that implement the
     * updateable interface
     * 
     * @param elapsedTime the time change during each update call
     */
    public void performUpdateableAttributes (long elapsedTime)
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


    /**
     * returns a String representation of an Attribute User
     */
    public String toString ()
    {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(getName() + "\n");
        for (Attribute attribute : getAttributes())
        {
            toReturn.append(attribute.toString());
            toReturn.append("\n");
        }
        return toReturn.toString();
    }


    /**
     * Abstract method which returns the name of the attribute user
     * 
     * @return String of the name of the attribute user
     */
    public abstract String getName ();

}
