package platforms.platformtypes;

import com.golden.gamedev.object.collision.CollisionGroup;

import sprite.AnimatedGameSprite;
import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

public class PlatformAction implements CollisionAction {
	AbstractPlatform sprite;
	
	public void doBreak (CollisionContext ccntext, CollisionSpec cspec){		
		if (ccntext.getSide() == CollisionGroup.BOTTOM_TOP_COLLISION){
			//Do general break stuff;
			sprite.setActive(false);
		}
	}
	
	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = (AbstractPlatform) sprite;
	}

}
