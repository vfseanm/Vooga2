package enemies.movement;

import attributes.Attribute;
import attributes.Updateable;
//Ambitious

public class TeleportMovement extends Attribute implements Updateable
{
    private int myRangeX;
    private int myRangeY;
    private int myDelay;
    private int time;
    
    public TeleportMovement(int rangeX, int rangeY, int delay){
        myRangeX=rangeX;
        myRangeY=rangeY;
        myDelay=delay;
    }
    
    public void update (long elaspedTime)
    {
        if(myEnemy.isOnScreen()){
            if(time>=myDelay){
            myEnemy.setLocation(Math.random()*myRangeX,  Math.random()*myRangeY);
            time=0;
            }
        }
        time++;
        
    }
    public void modifyTeleportMovement(int rangeX, int rangeY){
        myRangeX+=rangeX;
        myRangeY+=rangeY;
    }

    @Override
    public String getName ()
    {
        return "TeleportMovement";
    }
    public String toSting(){
        return "Attribute TeleportMovement";
    }

}
