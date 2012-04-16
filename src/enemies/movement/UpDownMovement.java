package enemies.movement;

import editor.editorConstructor;


// Idea master config file to control attribute exlusivity
// flying


@SuppressWarnings("serial")
public class UpDownMovement extends TwoPartMovement
{

    @editorConstructor(parameterNames = { "distance" , "duration"})
    public UpDownMovement (int distance, int duration)
    {
        super(distance,duration);
        
    }


    public void modifyUpDownMovement (int distance, int duration)
    {
        myDistance += distance;
        myPartDuration += duration;
    }


    protected void movementPart1 ()
    {
        myGameCharacter.setLocation(myGameCharacter.getX(), myGameCharacter.getY() + myDistance);
        

    }
 

    protected void movementPart2 ()
    {
        myGameCharacter.setLocation(myGameCharacter.getX(), myGameCharacter.getY() - myDistance);
        
    }


    @Override
    public String getName ()
    {
        return "UpDownMovement";
    }


    public String toString ()
    {
        return "Attribute UpDownMovement my distance is " + myDistance +
               " my time to turn is " + myPartDuration;
    }

}
