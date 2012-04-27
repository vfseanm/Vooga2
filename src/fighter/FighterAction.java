package fighter;

import attributes.Attribute;

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
			sprite.setY(ccntext.getOtherSprite(sprite).getY()-sprite.getHeight());
			sprite.modifyAttribute("Jump",true);
		
		}
	}
	
	public void fighterHitObject (CollisionContext ccntext, CollisionSpec cspec){ 		
		if (ccntext.getSide() == CollisionGroup.LEFT_RIGHT_COLLISION){
			sprite.setX(ccntext.getOtherSprite(sprite).getX()-sprite.getWidth());
		}
		if (ccntext.getSide() == CollisionGroup.RIGHT_LEFT_COLLISION){
			sprite.setX(ccntext.getOtherSprite(sprite).getX()+ccntext.getOtherSprite(sprite).getWidth());
			
		}
		if (ccntext.getSide() == CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite.setY(ccntext.getOtherSprite(sprite).getY()+ccntext.getOtherSprite(sprite).getHeight());
		}

	}
	
	public void fighterGetPowerUp(CollisionContext ccntext, CollisionSpec cspec) {
		try
        {
            BonusObject bonus = (BonusObject) ccntext.getOtherSprite(sprite);
            for (Attribute toAdd: bonus.getAttributesToOffer()) {
            	sprite.addAttribute(toAdd);
            }
            // bonus.setActive(false);
        } catch (ClassCastException e)
        {
            System.out.println("You have implemented the collision framework incorrectly. The fighterGetPowerUp method is meant to be used with PowerUps.");
        }

	}
	

	public void instantFighterDeath (CollisionContext ccntext, CollisionSpec cspec){
		
		if ( (ccntext.getSide() != CollisionGroup.TOP_BOTTOM_COLLISION) ){
			
			sprite.setLocation(-10000, -1000);
		}
		}

	
	public void fighterLoseHitpoints (CollisionContext ccntext, CollisionSpec cspec){
		sprite.modifyAttribute("Hitpoints", -10);
	}
	
	public void fighterLoseLife (CollisionContext ccntext, CollisionSpec cspec){
		sprite.modifyAttribute("NumberOfLives", -1);
	}

	public void setSprite(AnimatedGameSprite sprite) {
		try 
		{
			this.sprite = (Fighter) sprite;
		}
		catch (ClassCastException e) {
			System.out.println("You attempted to use a Fighter collision method with a sprite that is not a Fighter.");
		}
	}

	
}
