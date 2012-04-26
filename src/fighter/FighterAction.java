package fighter;

import java.util.ArrayList;

import attributes.Attribute;
import attributes.Hitpoints;
import attributes.NumberOfLives;
import bonusobjects.BonusObject;

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
			sprite.allowAttribute("Jump", true);
			sprite.allowAttribute("Gravity", false);
		}
	}
	
	public void fighterGetPowerUp(CollisionContext ccntext, CollisionSpec cspec) {
		try
        {
            BonusObject bonus = (BonusObject) ccntext.getOtherSprite(sprite);
            for (Attribute toAdd: bonus.getAttributesToOffer()) {
            	sprite.addAttribute(toAdd);
            }
            bonus.setActive(false);
        } catch (ClassCastException e)
        {
            System.out.println("You have implemented the collision framework incorrectly. The fighterGetPowerUp method is meant to be used with PowerUps.");
        }
	}
	
	
	public void fighterGetCarryable(CollisionContext ccntext, CollisionSpec cspec) {
		try
        {
            BonusObject bonus = (BonusObject) ccntext.getOtherSprite(sprite);
            sprite.addCarryableAttributes(bonus.getAttributesToOffer());
            bonus.setActive(false);
        } catch (ClassCastException e)
        {
            System.out.println("You have implemented the collision framework incorrectly. The fighterGetCarryable method is meant to be used with Carryables.");
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
