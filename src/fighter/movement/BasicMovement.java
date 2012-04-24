package fighter.movement;

import character.GameCharacter;

import com.golden.gamedev.engine.BaseInput;

import editor.editorConstructor;

import java.awt.event.KeyEvent;
import attributes.*;

@SuppressWarnings("serial")
public class BasicMovement extends Attribute implements Updateable, Movement, Input {

	public BaseInput 	myUserInput;
	public double 		myHorizMovement;
	public boolean		facingRight;
	public boolean 		facingLeft;
	
	@editorConstructor(parameterNames = { "horizontal movement" })
	public BasicMovement(double horizMove) {
	    super(horizMove);
		 if (horizMove < 0) 
	        	throw new RuntimeException("You must enter a positive number for the horizontal movement");
		myHorizMovement = horizMove;
	}
	
	
	@Override
	public String getName() {
		return "BasicMovement";
	}


	public void update(long elapsedTime) {
		if (isActive) {
			if (myUserInput.isKeyDown(KeyEvent.VK_LEFT)) {
				myGameCharacter.moveX(-myHorizMovement);
				facingRight = false;
				facingLeft = true;
			}

			if (myUserInput.isKeyDown(KeyEvent.VK_RIGHT)) {
				myGameCharacter.moveX(myHorizMovement);
				facingRight = true;
				facingLeft = false;
			}
		}
	}

	
	public void invert() {
		myHorizMovement = -myHorizMovement;
	}	
	
	
	public Object clone()
	{
	    return new BasicMovement(myHorizMovement);
	}

	
	public double getHorizMovement() {
		if (isActive) return myHorizMovement;
		return 0;
	}
	
	public double getVertMovement() {
		return 0;
	}
	
	/**
	 * Method for getting the direction the GameCharacter is facing to enable                           
	 * shooting in proper direction
	 *         
	 * @return array with [0] = whether the GameCharacter is facing left (true/false)
	 * 					  [1] = whether the GameCharacter is facing right (true/false)
	 */
	public boolean[] getDirection() {
		boolean[] directions = {facingLeft, facingRight};
		return directions;
	}
	
	public void setGameCharacter(GameCharacter gameCharacter) {
		myGameCharacter = gameCharacter;
	}


	public void setUserInput(BaseInput userInput) {
	    myUserInput = userInput;
	}
	
    public String toJson()
    {
        return myHorizMovement+"";
    }
    
    public static BasicMovement fromJson(String json)
    {

        double movement = Double.parseDouble(json);
        return new BasicMovement(movement);
    }

}
