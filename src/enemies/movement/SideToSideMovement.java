package enemies.movement;

import editor.editorConstructor;

//Change location to coordinate

@SuppressWarnings("serial")
public class SideToSideMovement extends TwoPartMovement
{
    

    @editorConstructor(parameterNames = { "distance" , "duration"})
    public SideToSideMovement (int distance, int duration)
    {
        super(distance,duration);
        
    }
    

    protected void movementPart1 ()
    {
        myGameCharacter.setLocation(myGameCharacter.getX()-myDistance, myGameCharacter.getY());
        

    }


    protected void movementPart2 ()
    {
       myGameCharacter.setLocation(myGameCharacter.getX()+myDistance,myGameCharacter.getY());
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
