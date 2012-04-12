package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;
// similiar to side to side
// hmm?


public class UpDownMovement extends Attribute implements Updateable
{
    private int myDistance;
    private int myTurnTime;
    private int time;
    private boolean up = false;

    @editorConstructor(parameterNames = { "distance" , "time"})
    public UpDownMovement (int distance, int time)
    {
        myDistance = distance;
        myTurnTime = time;
        up = true;
        this.time = time / 2;
    }


    public void modifyUpDownMovement (int distance, int time)
    {
        myDistance += distance;
        myTurnTime += time;
    }


    public void update (long elaspedTime)
    {
        if (up)
        {
            moveUp();
        }
        else
        {
            moveDown();

        }

    }


    private void moveDown ()
    {
        myEnemy.setLocation(myEnemy.getX(), myEnemy.getY() + myDistance);
        time++;
        if (time >= myTurnTime)
        {
            time = 0;
            up = true;
        }

    }


    private void moveUp ()
    {
        myEnemy.setLocation(myEnemy.getX(), myEnemy.getY() - myDistance);
        time++;
        if (time >= myTurnTime)
        {
            time = 0;
            up = false;
        }
    }


    @Override
    public String getName ()
    {
        return "UpDownMovement";
    }


    public String toString ()
    {
        return "Attribute UpDownMovement my distance is " + myDistance +
               " my time to turn is " + myTurnTime;
    }

}
