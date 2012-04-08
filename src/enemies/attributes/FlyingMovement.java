package enemies.attributes;

import editor.editorConstructor;

public class FlyingMovement extends Attribute implements Updateable
{
    @editorConstructor(parameterNames = {""})
    public FlyingMovement()
    {
        
    }

    public void update (long elaspedTime)
    {
        // TODO write flying movement

    }


    @Override
    public String getName ()
    {
        return "FlyingMovement";
    }


    //Will update to reflect changes of class
    public String toString ()
    {
        return "Attribute FlyingMovement";
    }

}
