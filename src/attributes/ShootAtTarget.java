package attributes;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import fighter.*;

public class ShootAtTarget extends Attribute {

	Sprite 		myTarget;
	boolean 	canFire;
	Timer 		refireRate;
	
	public ShootAtTarget(Game game, Fighter fighter, Sprite target) {
		super(game, fighter);
		myTarget = target;
		myName = "shootattarget";
	}

	@Override
	public void doFunction() {
		if (isActive) {
			if (myGame.keyDown(KeyEvent.VK_SPACE) && canFire) {
				double horizDistance = myTarget.getX() - myFighter.getX();
				double vertDistance = myTarget.getY() - myFighter.getY();
				double ratio = vertDistance / horizDistance;
				
				if (horizDistance < 0)
					myFighter.getMissile().setSpeed(-0.7, -0.7 * ratio);
				else
					myFighter.getMissile().setSpeed(0.7, 0.7 * ratio);
				// ADD MISSILE TO PLAYFIELD HERE
			}
		}
	}

	public void setTarget(Sprite newTarget) {
		myTarget = newTarget;
	}
}
