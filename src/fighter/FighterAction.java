package fighter;

import sprite.AnimatedGameSprite;
import collisions.CollisionAction;

public class FighterAction implements CollisionAction{

	Fighter sprite;
	
	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = (Fighter) sprite;
	}
	
}
