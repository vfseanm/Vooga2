package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;

// knows gravity too well?
public class Flying extends Attribute implements Updateable
{

    

    @editorConstructor(parameterNames = { "" })
    public Flying ()
    {
        super();
        
    }


    public void modifyFlying (boolean isFlying)
    {
        isActive = isFlying;
    }


    public void update (long elaspedTime)
    {
        if (isActive) {
            myEnemy.allowAttribute("Gravity", false);
        }
        else
        {
            myEnemy.restoreOriginalAttribute("Gravity");
        }

    }
    
    


    @Override
    public String getName ()
    {
        return "Flying";
    }


    public String toString ()
    {
        return "Attribute Flying is " + isActive;
    }


    public void invert ()
    {
        //Maybe Andrew!
       myEnemy.invertAttribute("Gravity");
        
    }

}
