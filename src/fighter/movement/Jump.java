package fighter.movement;

import java.awt.event.KeyEvent;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Timer;

import attributes.Attribute;
import attributes.Updateable;

@SuppressWarnings("serial")
public class Jump extends Attribute implements Updateable {

	public BaseInput 	myUserInput;
	public Timer 		myTimer;
	public double 		myJumpDistance;
	public boolean		myCanJump;
	public boolean 		myAtJumpPeak;

	
	public Jump(BaseInput userInput, int time, double jumpDistance) {
		myUserInput = userInput;
		myTimer = new Timer(time);
		myJumpDistance = Math.abs(jumpDistance);
		myCanJump = true;
	}
	
	public void update(long elapsedTime) {
		if (myActivity) {
			if (!myCanJump) 
				myCanJump = myTimer.action(elapsedTime);
			if (myCanJump && myUserInput.isKeyDown(KeyEvent.VK_UP)) {
				myFighter.moveY(myJumpDistance);
			}
		}
	}
	
	public changeJumpDistance() {
		
	}
	
	public changeGravityEffect() {
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
