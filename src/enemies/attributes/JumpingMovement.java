package enemies.attributes;

import editor.editorConstructor;

public class JumpingMovement extends Attribute implements Updateable
{
    @editorConstructor(parameterNames = {""})
    public JumpingMovement()
    {
        
    }
    public void update (long elaspedTime)
    {
        //TODO write jumping movement

    }


    @Override
    public String getName ()
    {

        return "JumpingMovement";
    }


    //Will update to reflect changes of class
    public String toString ()
    {
        return "Attribute JumpingMovement";
    }

}
