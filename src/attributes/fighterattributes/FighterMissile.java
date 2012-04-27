package attributes.fighterattributes;

import java.awt.event.KeyEvent;

import playfield.SingletonPlayField;

import sprite.AnimatedGameSprite;

import weapons.Weapon;

import character.AttributeUser;

import com.golden.gamedev.engine.BaseInput;
import attributes.interfaces.Input;

import editor.editorConstructor;

@SuppressWarnings("serial")
public class FighterMissile implements Weapon, Input {

	private AnimatedGameSprite 		myMissile;
	private BaseInput 				myUserInput;
	private int 					myDamage;
	private int 					myDelay;
	private int 					myTimer;
	private double 					mySpeed;
	private boolean 				canFire;

	@editorConstructor(parameterNames = { "missile", "damage", "delay", "speed" })
	public FighterMissile(AnimatedGameSprite missile, int damage, int delay,
			double speed) {
		myMissile = missile;
		myDamage = damage;
		myDelay = delay;
		mySpeed = Math.abs(speed);
		myTimer = 0;
		canFire = true;
	}

	public void use(AttributeUser character) {

		if (myTimer == 0 && myUserInput.isKeyPressed((KeyEvent.VK_SPACE)) && canFire) {
			SingletonPlayField.getInstance().add(myMissile);
			myMissile.setLocation(character.getX(), character.getY());
			
			if (character.getAttributeByName("FighterBasicMovement") != null) {
				FighterBasicMovement mover = (FighterBasicMovement) character.getAttributeByName("FighterBasicMovement");
				if (mover.isFacingRight()) myMissile.setHorizontalSpeed(mySpeed);
				else myMissile.setHorizontalSpeed(-mySpeed);
			}

			canFire = false;
		} else if (myTimer > myDelay) {
			canFire = true;
			myTimer = 0;
		}
		myTimer++; 
	}

	// useful for collisons to inflict damage
	public double getDamage() {
		return myDamage;
	}

	public void invert() {
		myMissile.setHorizontalSpeed(-myMissile.getHorizontalSpeed());

	}

	public void setUserInput(BaseInput userInput) {
		myUserInput = userInput;
	}
}
