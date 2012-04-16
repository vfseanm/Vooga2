package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;


// go do isActive and use that
// turnaround
@SuppressWarnings("serial")
public class JumpingMovement extends Attribute implements Updateable
{
    private int myDistance;
    private int myTime;
    private int time;


    @editorConstructor(parameterNames = { "distance", "time" })
    public JumpingMovement (int distance, int delay)
    {

        super(distance, delay);
        myDistance = distance;
        myTime = delay;
<<<<<<< HEAD
        time=0;
        
        
    }
 

    public void allowJumpingMovement ()
    {
=======
>>>>>>> 253371ac01ae4cbcfa771cb15b96babb3667a5aa
        time = 0;

    }


    public void modifyJumpingMovement (int distance, int time)
    {
        myDistance += distance;
        myTime += time;
    }


    public void update (long elaspedTime)
    {
        if (isActive)
        {
            if (time <= myTime)
            {

                myEnemy.setY(myEnemy.getY() - myDistance);
                myEnemy.allowAttribute("Gravity", false);
            }

        }
        else
        {

            myEnemy.restoreOriginalAttribute("Gravity");
        }
        time++;

    }
    //Repeated Code Andrew Help
    public void invert(){
        myDistance=-myDistance;
    }

    @Override
    public String getName ()
    {
        return "JumpingMovement";
    }


    public String toString ()
    {
        return "Attribute JumpingMovement my jump distance is " + myDistance +
               " my jump time is " + myTime;

    }

}
