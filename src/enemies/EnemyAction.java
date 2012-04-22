package enemies;

import sprite.AnimatedGameSprite;

import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

import com.golden.gamedev.object.collision.CollisionGroup;


public class EnemyAction implements CollisionAction {
	Enemy sprite;
	
	public void standOnTop (CollisionContext ccntext, CollisionSpec cspec){ 		
		if (ccntext.getSide() == CollisionGroup.TOP_BOTTOM_COLLISION){
			sprite.setY(ccntext.getOtherSprite(sprite).getY()-sprite.getHeight()-1);
			(sprite).restoreOriginalAttribute("JumpingMovement");
		}
		else if ((ccntext.getSide()!=CollisionGroup.TOP_BOTTOM_COLLISION) && (ccntext.getSide()!=CollisionGroup.BOTTOM_TOP_COLLISION)){
			(sprite).invertAttribute("OneDirectionMovement");
		}
	
		else if(ccntext.getSide() == CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite.allowAttribute("JumpingMovement", false);
		}
	}
	
	public void instantDeath (CollisionContext ccntext, CollisionSpec cspec){
		if (ccntext.getSide() != CollisionGroup.BOTTOM_TOP_COLLISION){
		}
	}
	
	public void attack (CollisionContext ccntext, CollisionSpec cspec){
		if ((ccntext.getSide() == CollisionGroup.LEFT_RIGHT_COLLISION) || (ccntext.getSide() == CollisionGroup.RIGHT_LEFT_COLLISION)){
			sprite.attack(2);
		}
	}

	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = (Enemy) sprite;
	}
}
