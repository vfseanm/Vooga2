package collisions;

import bonusobjects.Carryable;
import bonusobjects.PowerUp;

import character.GameCharacter;

import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

public class BonusObjectAction implements ActionPerformer {

	public void powerUpAction(GameCharacter sprite1, PowerUp sprite2){ 
		sprite1.addPowerUp(sprite2);
	}
	
	public void carryableAction(Fighter sprite1, Carryable sprite2) {
		sprite1.addCarryableAttributes(sprite2.getAttributesToOffer());
	}
	
	public void action(Sprite sprite1, Sprite sprite2, int collisionType) {
		// TODO Auto-generated method stub
		
	}
	
}

	
