package enemies;

import java.util.List;

import sprite.AnimatedGameSprite;

import attributes.Attribute;
import attributes.sharedattributes.Hitpoints;
import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

import com.golden.gamedev.object.collision.CollisionGroup;


public class EnemyAction implements CollisionAction {
	Enemy sprite;


	public void enemyStandOnTop (CollisionContext ccntext, CollisionSpec cspec){ 	
		if (ccntext.getSide() == CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite.setY(ccntext.getOtherSprite(sprite).getY()-sprite.getHeight());
			(sprite).restoreOriginalAttribute("JumpingMovement");
		}
		else if ((ccntext.getSide()!=CollisionGroup.TOP_BOTTOM_COLLISION) && (ccntext.getSide()!=CollisionGroup.BOTTOM_TOP_COLLISION)){
			(sprite).invertAttribute("OneDirectionMovement");
		}

		else if(ccntext.getSide() == CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite.allowAttribute("JumpingMovement", false);
		}
	}
	public void enemyHitObject (CollisionContext ccntext, CollisionSpec cspec){ 		
		if (ccntext.getSide() == CollisionGroup.LEFT_RIGHT_COLLISION){
			sprite.setX(ccntext.getOtherSprite(sprite).getX()-sprite.getWidth());
		}
		if (ccntext.getSide() == CollisionGroup.RIGHT_LEFT_COLLISION){
			sprite.setX(ccntext.getOtherSprite(sprite).getX()+ccntext.getOtherSprite(sprite).getWidth());
			
		}
	}

	public void instantEnemyDeath (CollisionContext ccntext, CollisionSpec cspec){
		//System.out.println("calling enemy death");
		if ( (ccntext.getSide() == CollisionGroup.TOP_BOTTOM_COLLISION) ){
			//System.out.println (ccntext.getSide());
			sprite.setLocation(-10000, -1000);
		}
	}

	public void enemyLoseLife (CollisionContext ccntext, CollisionSpec cspec){
		//Make sure the collision only happens once
		if (ccntext.getSide() != CollisionGroup.BOTTOM_TOP_COLLISION){
			List<Attribute> ability = sprite.getAttributes(); 
			for (Attribute skill: ability){
				if (skill.getName().equals("Hitpoints")){
					((Hitpoints)skill).modifyHitpoints(-10);
					//System.out.println ((Hitpoints)skill);
				}
			}
		}
	}

	/*public void attack (CollisionContext ccntext, CollisionSpec cspec){
		if ((ccntext.getSide() == CollisionGroup.LEFT_RIGHT_COLLISION) || (ccntext.getSide() == CollisionGroup.RIGHT_LEFT_COLLISION)){
			sprite.attack(2);
		}
	}*/

	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = (Enemy) sprite;
	}
}