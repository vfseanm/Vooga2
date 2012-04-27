package bonusobjects;

import java.util.List;

import attributes.Attribute;
import attributes.sharedattributes.Hitpoints;

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
			(sprite).restoreOriginalAttribute("JumpingMovement");
		}
		else if ((ccntext.getSide()!=CollisionGroup.TOP_BOTTOM_COLLISION) && (ccntext.getSide()!=CollisionGroup.BOTTOM_TOP_COLLISION)){
			(sprite).invertAttribute("OneDirectionMovement");
		}

		else if(ccntext.getSide() == CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite.allowAttribute("JumpingMovement", false);
		}
	}

	public void instantBonusObjectDestruction (CollisionContext ccntext, CollisionSpec cspec){
		if (ccntext.getSide() != CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite.setLocation(-10000, -1000);
			sprite.setActive(false);
		}
	}

	public void bonusObjectLoseLife (CollisionContext ccntext, CollisionSpec cspec){
		if (ccntext.getSide() != CollisionGroup.BOTTOM_TOP_COLLISION){
			List<Attribute> ability = sprite.getAttributes(); 
			for (Attribute skill: ability){
				if (skill.getName().equals("Hitpoints")){
					((Hitpoints)skill).modifyHitpoints(-10);
					System.out.println ((Hitpoints)skill);
				}
			}
		}
	}
	
	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = (BonusObject) sprite;
	}

}
