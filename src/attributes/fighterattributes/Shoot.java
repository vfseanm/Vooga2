package attributes.fighterattributes;

import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import weapons.Weapon;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Timer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import attributes.Attribute;
import attributes.enemyattributes.SideToSideMovement;
import attributes.interfaces.Input;
import attributes.interfaces.Updateable;

import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.Jsonable;

// FINISH IMPLEMENTING
@SuppressWarnings("serial")
public class Shoot extends Attribute implements Updateable, Input {

	private BaseInput	myUserInput;
	private Weapon		myWeapon;
	private int			myRefireRate;
	private Timer 		myTimer;
	private boolean 	canFire;
	
	@editorConstructor(parameterNames = { "weapon", "delay" })
	public Shoot(Weapon weapon, int delay) {
		super(weapon, delay);
		myWeapon = weapon;
		myRefireRate = delay;
		myTimer = new Timer(delay);
	}

	public void update(long elapsedTime) {
		if (isActive) {
			if (!canFire) {
				canFire = myRefireRate.action(elapsedTime);
			}

			if (myUserInput.isKeyPressed((KeyEvent.VK_SPACE)) && canFire) {
				myWeapon.setSpeed(7,7);
				canFire = false;
			}
		}
	}
	
	@Override
	public String getName() {
		return "Shoot";
	}


	public Object clone() {
		return new Shoot(myWeapon, myRefireRate);
	}

	public void invert() {}

	public void setUserInput(BaseInput userInput) {
		myUserInput = userInput;
	}
}
