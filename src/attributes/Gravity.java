package attributes;


import com.google.gson.Gson;


import editor.editorConstructor;
import editor.frameworks.Framework;
import editor.json.Jsonable;

import fighter.movement.Movement;

@SuppressWarnings("serial")
public class Gravity extends Attribute implements Updateable, Movement, Jsonable
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
        	myGameCharacter.moveY(myDistance);
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
        Gson gson = new Gson();
        return gson.toJson(myDistance);
    }
    
    public static Gravity fromJson(String json)
    {

        Gson gson = new Gson();
        double distance = gson.fromJson(json, double.class);
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
	
/*	private Gravity(){}
    public static ObjectFromJsonFactory getFactory()
    {
        return new ObjectFromJsonFactory(new Gravity());
    }*/
   


}
