package enemies.movement;

import attributes.Attribute;
import attributes.Updateable;

public class UpDownMovement extends Attribute implements Updateable
{
    private int myDistance;
    private int myTurnTime;
    private int time;
    private boolean up = false;
    
    public UpDownMovement(int distance, int time){
        myDistance = distance;
        myTurnTime = time;
        up = true;
        this.time = time / 2;
    }
    public void update (long elaspedTime)
    {
       
        
    }

    @Override
    public String getName ()
    {
        return "UpDownMovement";
    }

}
