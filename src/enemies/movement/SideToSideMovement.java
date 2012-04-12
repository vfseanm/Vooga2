package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;


public class SideToSideMovement extends Attribute implements Updateable
{
    private int myDistance;
    private int myTurnTime;
    private int time;
    private boolean left = false;

    @editorConstructor(parameterNames = { "distance" , "time"})
    public SideToSideMovement (int distance, int time)
    {
        myDistance = distance;
        myTurnTime = time;
        left = true;
        this.time = time / 2;
    }


    public void update (long elaspedTime)
    {
        
        if (left)
        {
            moveLeft();
        }
        else
        {
            moveRight();
        }

    }


    private void moveLeft ()
    {
        myEnemy.setLocation(myEnemy.getX()-myDistance, myEnemy.getY());
        time++;
        if(time>=myTurnTime){
            time=0;
            left=false;
        }

    }


    private void moveRight ()
    {
        myEnemy.setLocation(myEnemy.getX()+myDistance, myEnemy.getY());
        time++;
        if(time>=myTurnTime){
            time=0;
            left=true;
        }

    }
    
    public void modifySideToSideMovement(int distance, int time){
        myDistance+=distance;
        myTurnTime+=time;
    }


    @Override
    public String getName ()
    {
        return "SideToSideMovement";
    }


    public String toString ()
    {
        return "Attribute SideToSideMovement my distance is " + myDistance + " my time to turn is" + myTurnTime;
    }

}
