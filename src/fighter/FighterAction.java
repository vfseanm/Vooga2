package fighter;

import java.util.ArrayList;

import attributes.Attribute;
import attributes.Hitpoints;
import attributes.NumberOfLives;

import com.golden.gamedev.object.collision.CollisionGroup;

import sprite.AnimatedGameSprite;
import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

public class FighterAction implements CollisionAction{

	Fighter sprite;
	
	public void fighterStandOnTop (CollisionContext ccntext, CollisionSpec cspec){ 		
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
	
	public void instantFighterDeath (CollisionContext ccntext, CollisionSpec cspec){
		if (ccntext.getSide() != CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite.setLocation(-10000, -1000);
		}
	}
	
	public void fighterLoseHitpoints (CollisionContext ccntext, CollisionSpec cspec){
			ArrayList<Attribute> ability = (ArrayList<Attribute>) sprite.getAttributes(); 
			
			for (Attribute skill: ability){
				if (skill.getName().equals("Hitpoints")){
					((Hitpoints)skill).modifyHitpoints(-10);
				}
			}
	}
	
	public void fighterLoseLife (CollisionContext ccntext, CollisionSpec cspec){
			ArrayList<Attribute> ability = (ArrayList<Attribute>) sprite.getAttributes(); 
			
			for (Attribute skill: ability){
				if (skill.getName().equals("NumberOfLoves")){
					((NumberOfLives) skill).modifyNumberOfLives(-1);
				}
			}
	}

	public void setSprite(AnimatedGameSprite sprite) {
		this.sprite = (Fighter) sprite;
	}

	
}
