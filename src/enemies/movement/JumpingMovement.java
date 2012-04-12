package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;

//ground movement
public class JumpingMovement extends Attribute implements Updateable
{
    private int myDistance;
    private int myTime;
    private int time;


    @editorConstructor(parameterNames = { "distance", "time" })
    public JumpingMovement (int distance, int time)
    {

        myDistance = distance;
        myTime = time;
        this.time=0;

    }


    public void modifyJumpingMovement ()
    {
        time = 0;

    }


    public void modifyJumpingMovement (int distance, int time)
    {
        myDistance += distance;
        myTime += time;
    }


    public void update (long elaspedTime)
    {

        if (time<=myTime)
        {

            myEnemy.setY(myEnemy.getY() - myDistance);
            myEnemy.updateAttribute("Gravity", 0);
        }
        else{
            
            myEnemy.updateAttribute("Gravity");
        }
        time++;

    }


    @Override
    public String getName ()
    {
        return "JumpingMovement";
    }


    public String toString ()
    {
        return "Attribute JumpingMovemet my jump distance is "+myDistance +" my jump time is " +myTime;

    }

}
