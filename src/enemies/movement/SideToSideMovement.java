package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;
//Change location to coordinate

public class SideToSideMovement extends TwoPartMovement
{
    

    @editorConstructor(parameterNames = { "distance" , "duration"})
    public SideToSideMovement (int distance, int duration)
    {
        super(distance,duration);
        
    }
    




    protected void movementPart1 ()
    {
        myEnemy.setLocation(myEnemy.getX()-myDistance, myEnemy.getY());
        

    }


    protected void movementPart2 ()
    {
        myEnemy.setLocation(myEnemy.getX()+myDistance, myEnemy.getY());
        

    }
    
    public void modifySideToSideMovement(int distance, int duration){
        myDistance+=distance;
        myPartDuration+=duration;
    }


    @Override
    public String getName ()
    {
        return "SideToSideMovement";
    }


    public String toString ()
    {
        return "Attribute SideToSideMovement my distance is " + myDistance + " my time to turn is " + myPartDuration;
    }

}
