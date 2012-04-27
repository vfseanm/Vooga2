package weapons.fighterweapons;


import java.util.ResourceBundle;

import playfield.SingletonSpriteManager;
import sprite.AnimatedGameSprite;
import weapons.Weapon;


import character.AttributeUser;
import com.golden.gamedev.engine.BaseInput;
import attributes.interfaces.Input;

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
	private boolean					facingRight;
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
		if (myTimer == 0 && myUserInput.isKeyPressed(shootKey) && canFire)
		{
			SingletonSpriteManager.getInstance().add(myMissile);
			if (facingRight)
			{
				myMissile.setHorizontalSpeed(mySpeed);
				myMissile.setLocation(character.getX()+1, character.getY()/2);
			}
			else 
			{
				myMissile.setHorizontalSpeed(-mySpeed);
				myMissile.setLocation(character.getX()-1, character.getY()/2);
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

	public AnimatedGameSprite getMissile() {
		return myMissile;
	}
	
	public void modifyFighterMissile(boolean value) {
		facingRight = value;
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
