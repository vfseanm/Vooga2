package enemies.attributes;

import editor.editorConstructor;

public class GroundMovement extends Attribute implements Updateable
{
    @editorConstructor(parameterNames = {""})
    public GroundMovement()
    {
        
    }
    public void update (long elaspedTime)
    {
        // TODO write groundmovement
        
    }

    @Override
    public String getName ()
    {
        
        return "GroundMovement";
    }
    
  //Will update to reflect changes of class
    public String toString(){
        return "Attribute GroundMovement";
    }

}
