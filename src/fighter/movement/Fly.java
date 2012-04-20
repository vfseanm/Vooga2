package fighter.movement;

import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.engine.BaseInput;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import attributes.Attribute;
import attributes.Updateable;
import editor.editorConstructor;

@SuppressWarnings("serial")
public class Fly extends Attribute implements Updateable, Movement
{
	private BaseInput	myUserInput;
	private double		myFlightMovement;
	
    @editorConstructor(parameterNames = {"flight movement"})
    public Fly(double flightMovement)
    {
        super(flightMovement);   
        if (flightMovement < 0) 
        	throw new RuntimeException("You must enter a positive number for the flight movement");
        myFlightMovement = flightMovement;
    }


    public void update (long elapsedTime)
    {
    	if (isActive) {
			if (myUserInput.isKeyDown(KeyEvent.VK_UP)) {
				myGameCharacter.moveY(-myFlightMovement);
			}

			if (myUserInput.isKeyDown(KeyEvent.VK_DOWN)) {
				myGameCharacter.moveY(myFlightMovement);
			}
    	}
    } 


    @Override
    public String getName ()
    {
        return "Fly";
    }


    public String toString ()
    {
        return "Attribute Fly is " + isActive;
    }


    public void invert ()
    {
        myFlightMovement = -myFlightMovement;       
    }
    
    public Object clone()
    {
        return new Fly(myFlightMovement);
    }

	public double getHorizMovement() {
		return 0;
	}


	public double getVertMovement() {
		if (isActive) return myFlightMovement;
		return 0;
	}
	
	public String toJson()
    {
        Gson gson = new Gson();
        List<Double> argList = new ArrayList<Double>();
        argList.add(myFlightMovement);
        return gson.toJson(argList);
    }
    
    public static Fly fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Double>>(){}.getType();
        List<Double> argList = gson.fromJson(json, collectionType);
        return new Fly(argList.get(0));
    }
}
