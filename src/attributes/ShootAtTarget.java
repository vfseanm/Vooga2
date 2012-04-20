package attributes;

import java.awt.event.KeyEvent;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.*;

import editor.editorConstructor;
import sprite.*;

@SuppressWarnings("serial")
public class ShootAtTarget extends Attribute implements Updateable {

	@Override
	public String getName() {
		return "ShootAtTarget";
	}

	public BaseInput 				myUserInput;
	public AnimatedGameSprite 		myTarget;
	public boolean 					myCanFire;
	public Timer 					myRefireRate;
	public int                      myRate;

	@editorConstructor(parameterNames = {"target", "refire rate" })
	public ShootAtTarget(BaseInput userInput, AnimatedGameSprite target, int refireRate) {
		super(userInput, target, refireRate);
		myUserInput = userInput;
		myTarget = target;
		myCanFire = true;
		myRefireRate = new Timer(refireRate);
		myRate = refireRate;
	}

	public void setTarget(AnimatedGameSprite newTarget) {
		myTarget = newTarget;
	}

	public void update(long elapsedTime) {
		
		if (!myCanFire) {
			myCanFire = myRefireRate.action(elapsedTime);
		}
		
		if (myUserInput.isKeyPressed((KeyEvent.VK_SPACE)) && myCanFire) {
			double horizDistance = myTarget.getX() - myGameCharacter.getX();
			double vertDistance = myTarget.getY() - myGameCharacter.getY();
			double ratio = vertDistance / horizDistance;

			if (horizDistance < 0)
				myGameCharacter.getMissile().setSpeed(-0.7, -0.7 * ratio);
			else
				myGameCharacter.getMissile().setSpeed(0.7, 0.7 * ratio);
			
			myCanFire = false;
		}
	}

	public void invert() {
		// TODO Auto-generated method stub
		
	}
	
	public Object clone()
	{
	    return new ShootAtTarget(myUserInput, myTarget, myRate);
	}
	

}
