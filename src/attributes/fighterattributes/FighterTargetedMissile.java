package attributes.fighterattributes;

import java.awt.event.KeyEvent;

import attributes.Attribute;
import attributes.interfaces.Input;
import attributes.interfaces.Updateable;

import character.AttributeUser;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.*;

import editor.editorConstructor;
import sprite.*;
import weapons.Weapon;

@SuppressWarnings("serial")
public class FighterTargetedMissile implements Weapon, Input{

	public BaseInput 				myUserInput;
	public AnimatedGameSprite 		myTarget;
	public boolean 					myCanFire;
	public Timer 					myRefireRate;
	public int                      myRate;

	@editorConstructor(parameterNames = {"target", "refire rate" })
	public FighterTargetedMissile(BaseInput userInput, AnimatedGameSprite target, int refireRate) {
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
	    return new FighterTargetedMissile(myUserInput, myTarget, myRate);
	}

	public void use(AttributeUser character) {
		// TODO Auto-generated method stub
		
	}

	public void setUserInput(BaseInput userInput) {
		// TODO Auto-generated method stub
		
	}
	

}
