package attributes.sharedattributes;


import attributes.Attribute;
import attributes.interfaces.Movement;
import attributes.interfaces.Updateable;

import com.google.gson.Gson;


import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;


@SuppressWarnings("serial")
public class Gravity extends Attribute implements Updateable, Movement, JsonableAttribute
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
        	myAttributeUser.moveY(myDistance);
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
    
    public Gravity fromJson(String json)
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
	
	private Gravity(){}
    public static AttributeFactory<Gravity> getFactory()
    {
        return new AttributeFactory<Gravity>(new Gravity());
    }
   


}

