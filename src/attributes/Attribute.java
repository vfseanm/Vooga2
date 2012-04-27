package attributes;

import java.io.Serializable;
import character.AttributeUser;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
// import com.sun.tools.internal.xjc.model.Constructor;
import java.lang.reflect.*;


/**
 * This class is used to allow for dynamic attribute allocation to enemies and
 * fighters. In essence, this allows for flexibility in the framework while
 * still providing functionality by defining some subclasses of Attribute. The
 * enemies and fighter both have a list of Attributes, whose handling is defined
 * in the GameCharacter superclass for uniformity.
 * 
 * @authors Alex & Tori
 */
@SuppressWarnings("serial")
public abstract class Attribute implements Serializable, Cloneable
{

    protected AttributeUser myAttributeUser;
    protected Object myOriginal;
    protected boolean isActive;
    public static boolean makeOriginal = true;


    public abstract String getName ();


    /**
     * associates an attribute with the given enemy/fighter or other attribute user
     * 
     * @param user the attribute user to be bound to the attribute
     */


    public void setAttributeUser(AttributeUser user) {
        myAttributeUser = user;

    }


    /**
     * sets the isActive of an attribute to the passed parameter of active
     * 
     * @param active
     */
    public void setActivity (boolean active)
    {
        isActive = active;
    }


    /**
     * returns the activity
     * 
     * @return isActive
     */
    public boolean getActivity ()
    {
        return isActive;
    }


    /**
     * an abstract method to help the user know to implement the clone feature
     */
    public abstract Object clone ();


    /**
     * allos a method to help the user to implement Json
     * 
     * @return
     */
    public String toJson ()
    {
        return "";
    }


    /**
     * Constructor for attributes All attributes need to call this super
     * constructor and pass their parameters to the super constructor this will
     * create an original version of an attribute which can then later be
     * restored
     * 
     * @param o the parameters of the subclass constructor
     */
    public Attribute (Object ... o)
    {
        isActive = true;
        if (makeOriginal == true)
        {
            makeOriginal = false;

            Constructor<?>[] c = this.getClass().getConstructors();

            try
            {

                for (@SuppressWarnings("rawtypes")
                Constructor constructor : c)
                {
                    if (constructor.getModifiers() == Modifier.PUBLIC)
                    {
                        if (o.length == constructor.getParameterTypes().length)
                        {
                            myOriginal = constructor.newInstance(o);
                            break;
                        }
                    }

                }
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            catch (InstantiationException e)
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
        else
        {
            makeOriginal = true;
        }
    }


    /**
     * this method is used to restore all attributes to their original state
     * only works if the super constructor is properly called in the subclass
     */
    public void restoreOriginalAttribute ()
    {
        isActive = true;
        Field[] myFields = this.getClass().getDeclaredFields();
        Field[] myOriginalFields = myOriginal.getClass().getDeclaredFields();
        for (int i = 0; i < myOriginalFields.length; i++)
        {
            try
            {

                myFields[i].setAccessible(true);
                myOriginalFields[i].setAccessible(true);
                myFields[i].set(this, myOriginalFields[i].get(myOriginal));

            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}
