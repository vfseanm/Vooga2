package attributes;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import com.sun.tools.internal.xjc.model.Constructor;
import enemies.Enemy;
import enemies.movement.JumpingMovement;
import fighter.*;


/**
 * This class is used to allow for dynamic attribute allocation to enemies In
 * essence this allows for flexibility in the framework whilst still providing
 * functionality by defining some subclasses of Attribute The enemy class has a
 * List of attributes
 * 
 * @author Alex
 */
@SuppressWarnings("serial")
public abstract class Attribute implements Serializable
{
    protected Enemy myEnemy;
    protected Fighter myFighter;
    protected boolean isActive;
    public static boolean makeOriginal = false;
    protected Object myOriginal;


    public abstract String getName ();


    public Attribute (Object...o)
    {
        if (makeOriginal == false)
        {
            makeOriginal = true;
           
            java.lang.reflect.Constructor<?>[] c = this.getClass().getConstructors();
           
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
            makeOriginal = false;
        }
    }


    //associates an attribute with the given enemy
    public void setEnemy (Enemy enemy)
    {
        myEnemy = enemy;
    }


    // associates an attribute with the fighter
    public void setFighter (Fighter fighter)
    {
        myFighter = fighter;
    }


    public void setActivity (boolean active)
    {
        isActive = active;
    }


    public void restoreOriginalAttribute ()
    {
        
        Field[] myFields = this.getClass().getDeclaredFields();
        Field[] myOriginalFields =  myOriginal.getClass().getDeclaredFields();
        for (int i = 0; i < myOriginalFields.length; i++)
        {
            try
            {
                System.out.println(myFields[i].get("Hey"+this));
                System.out.println("cs"+myOriginalFields[i].get(myOriginal));
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
