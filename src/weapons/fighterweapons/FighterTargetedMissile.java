package weapons.fighterweapons;
import java.awt.event.KeyEvent;

import java.util.List;
import java.util.ResourceBundle;

import playfield.SingletonSpriteManager;

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
			SingletonSpriteManager.getInstance().add(myMissile);
			myMissile.setLocation(character.getX(), character.getY());
			
			List<AnimatedGameSprite> otherSprite = SingletonSpriteManager.getInstance().getMySprites();
		

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
