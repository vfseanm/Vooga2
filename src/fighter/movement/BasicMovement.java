package fighter.movement;

import com.golden.gamedev.engine.BaseInput;
import java.awt.event.KeyEvent;
import attributes.*;

@SuppressWarnings("serial")
public class BasicMovement extends Attribute implements Updateable {

	public BaseInput 	myUserInput;
	public double 		myHorizMovement;
	
	
	public BasicMovement(BaseInput userInput, double horizMove) {
		myUserInput = userInput;
		myHorizMovement = Math.abs(horizMove);
	}
	
	
	@Override
	public String getName() {
		return "BasicMovement";
	}


	public void update(long elaspedTime) {
		
		if (myUserInput.isKeyDown(KeyEvent.VK_LEFT)) 
			myFighter.moveX(-myHorizMovement);
		
		if (myUserInput.isKeyDown(KeyEvent.VK_RIGHT)) 
			myFighter.moveX(myHorizMovement);
		
	}	

}
