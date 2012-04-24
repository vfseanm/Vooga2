package attributes;
import java.io.Serializable;


import character.GameCharacter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
//import com.sun.tools.internal.xjc.model.Constructor;
import java.lang.reflect.*;


/**
 * This class is used to allow for dynamic attribute allocation to enemies and fighters.
 * In essence, this allows for flexibility in the framework while still providing
 * functionality by defining some subclasses of Attribute.
 * The enemies and fighter both have a list of Attributes, whose handling is defined in
 * the GameCharacter superclass for uniformity.
 * 
 * @authors Alex & Tori
 */
@SuppressWarnings("serial")
public abstract class Attribute implements Serializable, Cloneable
{
    protected GameCharacter		myGameCharacter;	
    protected Object 			myOriginal;
    protected boolean 			isActive;
    public static boolean 		makeOriginal = true;   //Note, this is not thread-safe...Sorry multi-threaded programmers
    
    public abstract String getName();
    
    //associates an attribute with the given enemy/fighter
    public void setGameCharacter(GameCharacter gameCharacter) {
        myGameCharacter = gameCharacter;
    }
    
    // turns an attribute on/off (active/inactive)
    public void setActivity(boolean active) {
    	isActive = active;
    }
    
    public boolean getActivity() {
    	return isActive;
    }
    
    public abstract Object clone();
    
    public String toJson()
    {
        return "";
    }
    


    public Attribute (Object...o)
    {
        isActive=true;
        if (makeOriginal == true)
        {
            makeOriginal = false;
           
            Constructor<?>[] c = this.getClass().getConstructors();
           
            try
            {
                myOriginal =  c[0].newInstance(o);
            }
            catch (IllegalArgumentException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        else
        {
            makeOriginal = true;
        }
    }


    public void restoreOriginalAttribute ()
    {
        isActive=true;
        Field[] myFields = this.getClass().getDeclaredFields();
        Field[] myOriginalFields =  myOriginal.getClass().getDeclaredFields();
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
