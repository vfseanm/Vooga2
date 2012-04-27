package weapons.fighterweapons;

import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import playfield.SingletonSpriteManager;
import sprite.AnimatedGameSprite;
import weapons.Weapon;


import character.AttributeUser;

import com.golden.gamedev.engine.BaseInput;

import attributes.fighterattributes.FighterBasicMovement;
import attributes.interfaces.Input;

import editor.editorConstructor;

@SuppressWarnings("serial")
public class FighterMissile implements Weapon, Input {

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

	
	public FighterMissile(AnimatedGameSprite missile, int damage, int delay,
			double speed) {
		
		myMissile.setGroup("FIGHTERMISSILE");
		myMissile = missile;
		myDamage = damage;
		myDelay = delay;
		mySpeed = Math.abs(speed);
		myTimer = 0;
		canFire = true;
		shootKey = Integer.parseInt(myGameKeys.getString("SHOOT"));
	}

	public void use(AttributeUser character) {

		if (myTimer == 0 && myUserInput.isKeyPressed(shootKey) && canFire) {
			SingletonSpriteManager.getInstance().add(myMissile);
			
			if (character.getAttributeByName("FighterBasicMovement") != null) {
				FighterBasicMovement mover = (FighterBasicMovement) character.getAttributeByName("FighterBasicMovement");
				if (mover.isFacingRight())
				{
					myMissile.setHorizontalSpeed(mySpeed);
					myMissile.setLocation(character.getX()+1, character.getY()/2);
				}
				else 
				{
					myMissile.setHorizontalSpeed(-mySpeed);
					myMissile.setLocation(character.getX()-1, character.getY()/2);
				}
			}
			else {
				myMissile.setHorizontalSpeed(mySpeed);
				myMissile.setLocation(character.getX(), character.getY()/2);
			}
			canFire = false;
		}
		
		else if (myTimer > myDelay) 
		{
			canFire = true;
			myTimer = 0;
		}
		myTimer++; 
	}

	
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
