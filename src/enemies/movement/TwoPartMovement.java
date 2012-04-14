package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;

public abstract class TwoPartMovement extends Attribute implements Updateable
{
    protected int myDistance;
    protected int myPartDuration;
    private int time;
    private boolean part1;
    
    
    public TwoPartMovement(int distance,int duration){
        super(distance,duration);
        myDistance = distance;
        myPartDuration=duration;
        time=duration/2;
        part1=true;
    }
    
    public void setPart1(boolean ){
        part1=startPart1;
    }
    
}
