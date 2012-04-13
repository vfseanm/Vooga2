package attributes;

import editor.editorConstructor;

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
