package bonusobjects;

import com.golden.gamedev.object.collision.CollisionGroup;

import sprite.AnimatedGameSprite;
import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

public class BonusObjectAction implements CollisionAction{

	BonusObject sprite;
	
	public void bonusObjectStandOnTop (CollisionContext ccntext, CollisionSpec cspec){ 		
		if (ccntext.getSide() == CollisionGroup.TOP_BOTTOM_COLLISION){
			sprite.setY(ccntext.getOtherSprite(sprite).getY()-sprite.getHeight()-1);
			sprite.allowAttribute("Jump", true);
			sprite.allowAttribute("Gravity", false);
		}
	}
	
	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = (BonusObject) sprite;
	}

}
