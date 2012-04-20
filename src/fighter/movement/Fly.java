package fighter.movement;

import java.awt.event.KeyEvent;

import com.golden.gamedev.engine.BaseInput;

import attributes.Attribute;
import attributes.Updateable;
import editor.editorConstructor;

@SuppressWarnings("serial")
public class Fly extends Attribute implements Updateable, Movement
{
	private BaseInput	myUserInput;
	private double		myFlightMovement;
	
    @editorConstructor(parameterNames = {"flight movement"})
    public Fly(BaseInput userInput, double flightMovement)
    {
        super(userInput, flightMovement);   
        if (flightMovement < 0) 
        	throw new RuntimeException("You must enter a positive number for the flight movement");
        myFlightMovement = flightMovement;
    }


    public void update (long elapsedTime)
    {
    	if (myUserInput.isKeyDown(KeyEvent.VK_UP)) {
		    myGameCharacter.moveY(-myFlightMovement);
		}
		
		if (myUserInput.isKeyDown(KeyEvent.VK_DOWN)) {
			myGameCharacter.moveY(myFlightMovement);
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
        return new Fly(myUserInput, myFlightMovement);
    }
    
    public String toJson()
    {
        return myFlightMovement + "";
    }


	public double getHorizMovement() {
		// TODO Auto-generated method stub
		return 0;
	}


	public double getVertMovement() {
		// TODO Auto-generated method stub
		return 0;
	}
   

}
