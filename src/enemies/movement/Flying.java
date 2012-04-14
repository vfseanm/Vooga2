package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;

// knows gravity too well?
public class Flying extends Attribute implements Updateable
{

    private boolean flying;

    @editorConstructor(parameterNames = { "" })
    public Flying ()
    {
        flying = true;
    }


    public void modifyFlying (boolean isFlying)
    {
        flying = isFlying;
    }


    public void update (long elaspedTime)
    {
        if (flying) {
            myEnemy.updateAttribute("Gravity", 0);
        }
        else
        {
            myEnemy.updateAttribute("Gravity");
        }

    }
    
    public void resetFlyingOriginal(){
        myOriginal.
    }
    public void resetFlying(){
        flying =false;
    }


    @Override
    public String getName ()
    {
        return "Flying";
    }


    public String toString ()
    {
        return "Attribute Flying is " + flying;
    }

}
