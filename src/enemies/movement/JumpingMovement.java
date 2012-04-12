package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;


public class JumpingMovement extends Attribute implements Updateable
{
    @editorConstructor(parameterNames = { "distance", "time" })
    public JumpingMovement (int distance,int time){
    

    }

    public void modifyJumpingMovement(){
        
    }

    public void update (long elaspedTime)
    {

    }


    @Override
    public String getName ()
    {
        return "JumpingMovement";
    }


    public String toString ()
    {
        return "Attribute JumpingMovemet";

    }

}
