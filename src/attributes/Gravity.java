package attributes;

import editor.editorConstructor;
import fighter.movement.Movement;

@SuppressWarnings("serial")
public class Gravity extends Attribute implements Movement, Updateable
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

    public void update (long elapsedTime)
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
    
    public String toJson()
    {
        return myDistance+"";
    }
    
    public static Gravity fromJson(String json)
    {
        double distance = Double.parseDouble(json);
        return new Gravity(distance);
    }

    //why getters
	public double getHorizMovement() {
		return 0;
	}


	public double getVertMovement() {
		if (isActive) return myDistance;
		return 0;
	}

}
