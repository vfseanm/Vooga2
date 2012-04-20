package fighter.movement;

import com.golden.gamedev.engine.BaseInput;

import editor.editorConstructor;

import java.awt.event.KeyEvent;
import attributes.*;

@SuppressWarnings("serial")
public class BasicMovement extends Attribute implements Updateable, Movement {

	public BaseInput 	myUserInput;
	public double 		myHorizMovement;
	public boolean		facingRight;
	public boolean 		facingLeft;
	
	@editorConstructor(parameterNames = { "user input", "horizontal movement" })
	public BasicMovement(BaseInput userInput, double horizMove) {
	    super(userInput,horizMove);
		myUserInput = userInput;
		 if (horizMove < 0) 
	        	throw new RuntimeException("You must enter a positive number for the horizontal movement");
		myHorizMovement = horizMove;
	}
	
	
	@Override
	public String getName() {
		return "BasicMovement";
	}


	public void update(long elapsedTime) {
		if (myUserInput.isKeyDown(KeyEvent.VK_LEFT)) {
		    myGameCharacter.moveX(-myHorizMovement);
		}
		
		if (myUserInput.isKeyDown(KeyEvent.VK_RIGHT)) {
			myGameCharacter.moveX(myHorizMovement);
		}
	}

	
	public void invert() {
		myHorizMovement = -myHorizMovement;
	}	
	
	
	public Object clone()
	{
	    return new BasicMovement(myUserInput, myHorizMovement);
	}

	
	public double getHorizMovement() {
		if (isActive) return myHorizMovement;
		return 0;
	}
	
	public double getVertMovement() {
		return 0;
	}

}
