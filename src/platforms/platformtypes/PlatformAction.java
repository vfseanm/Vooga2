package platforms.platformtypes;

import sprite.AnimatedGameSprite;
import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

public class PlatformAction implements CollisionAction {
 	AnimatedGameSprite sprite;
	
	public void actionBreak (CollisionContext ccntext, CollisionSpec cspec){
		BreakablePlatform bplatform = (BreakablePlatform) sprite;
		bplatform.actionBreak(sprite, ccntext, cspec);;
	}
	
	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = sprite;
	}
	

}
