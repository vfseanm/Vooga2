package attributes;

import editor.editorConstructor;

@SuppressWarnings("serial")
public class Gravity extends Attribute implements Updateable
{
    private int myDistance;


    @editorConstructor(parameterNames = { "distance" })
    public Gravity (int distance)
    {
        super(distance);
        myDistance = distance;   
    }


    public void modifyGravityDistance (int distance)
    {
        myDistance = distance;

    }

    public void update (long elaspedTime)
    {
        myGameCharacter.setY(myGameCharacter.getY() + myDistance);
        
        if (isActive) {
        	myGameCharacter.setY(myGameCharacter.getY() + myDistance);
        }
    }


    public void invert(){
        myDistance=-myDistance;
    }


    @Override
    public String getName ()
    {
        return "Gravity";
    }


    public String toString ()
    {
        return "Attribute Gravity my distance is " + myDistance;
    }

}
