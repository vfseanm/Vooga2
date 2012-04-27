package attributes.fighterattributes;

import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import playfield.SingletonPlayField;

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

	transient protected ResourceBundle myGameKeys = ResourceBundle
    .getBundle("demo.GameKeysResourceBundle");
	
	private AnimatedGameSprite 		myMissile;
	private BaseInput 				myUserInput;
	private int 					myDamage;
	private int 					myDelay;
	private int 					myTimer;
	private double 					mySpeed;
	private boolean 				canFire;
	private int						shootKey;

	@editorConstructor(parameterNames = { "missile", "damage", "delay", "speed" })
	public FighterTargetedMissile(AnimatedGameSprite missile, int damage, int delay,
			double speed) {
		myMissile = missile;
		myDamage = damage;
		myDelay = delay;
		mySpeed = Math.abs(speed);
		myTimer = 0;
		canFire = true;
		shootKey = Integer.parseInt(myGameKeys.getString("SHOOT"));
	}

	public void use(AttributeUser character) {

		if (myTimer == 0 && myUserInput.isKeyPressed((shootKey)) && canFire) {
			SingletonPlayField.getInstance().add(myMissile);
			myMissile.setLocation(character.getX(), character.getY());
			
			// ADD ABOVE METHOD TO LOOP THROUGH ENEMIES & SHOOT AT CLOSEST

			canFire = false;
		} else if (myTimer > myDelay) {
			canFire = true;
			myTimer = 0;
		}
		myTimer++; 
	}

	public void invert() {
		// USE ABOVE METHOD TO LOOP THROUGH ENEMIES & SHOOT AT FURTHEST AWAY
	}

	public void setUserInput(BaseInput userInput) {
		myUserInput = userInput;
	}
	

}
