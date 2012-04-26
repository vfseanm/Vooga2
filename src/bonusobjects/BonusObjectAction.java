package bonusobjects;

import sprite.AnimatedGameSprite;
import collisions.CollisionAction;

public class BonusObjectAction implements CollisionAction{

	BonusObject sprite;
	
	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = (BonusObject) sprite;
	}

}
