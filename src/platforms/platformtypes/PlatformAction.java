package platforms.platformtypes;

import com.golden.gamedev.object.collision.CollisionGroup;

import sprite.AnimatedGameSprite;
import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

public class PlatformAction implements CollisionAction {
 	AnimatedGameSprite sprite;
	
	public void doBreak (CollisionContext ccntext, CollisionSpec cspec){
		Platform.doBreak(AnimatedGameSprite sprite, CollisionContext ccntext, CollisionSpec cspec);
	}
	
	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = sprite;
	}
	

}
