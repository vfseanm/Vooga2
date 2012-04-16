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
        time = 0;

    }


    public void modifyJumpingMovement (int distance, int time)
    {
        myDistance += distance;
        myTime += time;
    }


    public void update (long elapsedTime)
    {
        if (isActive)
        {
            if (time <= myTime)
            {

                myGameCharacter.setY(myGameCharacter.getY() - myDistance);
                myGameCharacter.allowAttribute("Gravity", false);
            }

        }
        else
        {

            myGameCharacter.restoreOriginalAttribute("Gravity");
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
