package platforms.platformtypes;

import platforms.fsmframework.PlatformSwitch;

import com.golden.gamedev.object.collision.CollisionGroup;

import sprite.AnimatedGameSprite;
import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

public class PlatformAction implements CollisionAction {
	AnimatedGameSprite sprite;

	public void actionBreak(CollisionContext ccntext, CollisionSpec cspec) {
		BreakablePlatform bplatform = (BreakablePlatform) sprite;
		bplatform.actionBreak(sprite, ccntext, cspec);
	}

	public void switchPlatform(CollisionContext ccntext, CollisionSpec cspec) {
		if (ccntext.getSide() == CollisionGroup.TOP_BOTTOM_COLLISION) {
			((PlatformSwitch) sprite).setOn(true);
		}
	}

	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = sprite;
	}

}
