package attributes;

import editor.editorConstructor;

@SuppressWarnings("serial")
public class Gravity extends Attribute implements Updateable
{
    private double myDistance;


    @editorConstructor(parameterNames = { "distance" })
    public Gravity (double distance)
    {
        super(distance);
        myDistance = distance;   
    }


    public void modifyGravityDistance (double distance)
    {
        myDistance = distance;

    }

    public void update (long elaspedTime)
    {
        
        
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
    
    public Object clone()
    {
        return new Gravity(myDistance);
    }

}
