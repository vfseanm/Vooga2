package attributes.enemyattributes;

import attributes.Attribute;
import attributes.interfaces.Updateable;


@SuppressWarnings("serial")
public abstract class TwoPartMovement extends Attribute implements Updateable
{
    protected double myDistance;
    protected int myPartDuration;
    private int time;
    private boolean isPart1;


    public TwoPartMovement (double distance, int duration)
    {
        super(distance, duration);
        myDistance = distance;
        myPartDuration = duration;
        time = duration / 2;
        isPart1 = true;
    }
    
    protected TwoPartMovement(){}


    public void setPart1 (boolean part1)
    {
        isPart1 = part1;
    }


    public void update (long elaspedTime)
    {
        if (isActive)
        {
            if (isPart1)
            {
                movementPart1();
            }
            else
            {
                movementPart2();
            }
            time++;
            if (time >= myPartDuration)
            {
                isPart1 = (!isPart1);
                time = 0;
            }
        }
    }


    public void invert ()
    {
        myDistance = -myDistance;
    }


    protected abstract void movementPart1 ();


    protected abstract void movementPart2 ();

}
