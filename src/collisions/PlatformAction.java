package collisions;

import platforms.BreakablePlatform;
import platforms.DecoratedPlatform;
import platforms.SimplePlatform;
import platforms.AbstractPlatform;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;
import fighter.Fighter;
import sprite.AnimatedGameSprite;


public class PlatformAction implements ActionPerformer{

	public void standardaction (AnimatedGameSprite sprite1, AbstractPlatform sprite2, int collisionType){ 
	    
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			if ( (sprite1.getX()+(sprite1.getWidth()) >= sprite2.getX())
					&& (sprite1.getX() <= sprite2.getX()+ sprite2.getWidth()) ){
				((Enemy) sprite1).updateAttribute("JumpingMovement");
				
			}
		}
	}
	private void action (Enemy sprite1, DecoratedPlatform sprite2, int collisionType){ 

	}

	private void action (Fighter sprite1, SimplePlatform sprite2, int collisionType){
		standardaction (sprite1, sprite2, collisionType);
	}
	

	public void action (Enemy sprite1, SimplePlatform sprite2, int collisionType){
		standardaction (sprite1, sprite2, collisionType);
	}

	private void action (Enemy sprite1, BreakablePlatform sprite2, int collisionType){
		standardaction (sprite1, sprite2, collisionType);
		if (collisionType ==  CollisionGroup.BOTTOM_TOP_COLLISION ){
			for (int i=0; i<=2; i++){
				//Nothing happens...set delay
			}
			sprite2.setActive(false);
		}
	}

	public void action(Sprite sprite1, Sprite sprite2, int collisionType) { 
		
	}

}

