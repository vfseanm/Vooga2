package fighter.attributes;

import com.golden.gamedev.Game;
import fighter.*;

public class Health extends Attribute {

	private int myCurrentHitPoints;
	private int myMaxHitPoints;
	
	public Health(Game game, Fighter fighter, int hitPoints) {
		super(game, fighter);
		myCurrentHitPoints = hitPoints;
		myMaxHitPoints = hitPoints;
		myName = "health";
	}

	public void doFunction() {
		// TODO Auto-generated method stub
		
	}
	
	public void updateAndCheckHP(int amount) {
		myCurrentHitPoints+=amount;
		if (myCurrentHitPoints > myMaxHitPoints) {
			myFighter.updateLives(1);
			myCurrentHitPoints = myCurrentHitPoints - myMaxHitPoints;
		}
		else if (myCurrentHitPoints <= 0) {
			myFighter.updateLives(-1);
			myCurrentHitPoints = myMaxHitPoints + myCurrentHitPoints;
		}
	}

}
