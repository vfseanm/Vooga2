package collisions;

 
import platforms.platformtypes.*;
 
import platforms.platformtypes.*;

 
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
				((Enemy) sprite1).restoreOriginalAttribute("JumpingMovement");
			}
		}
		
	}
	public void action (Enemy sprite1, RotatingPlatform sprite2, int collisionType){
		standardaction (sprite1, sprite2, collisionType);
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			//sprite2.doBehavior(1, 100);
 		}

	}
	
	public void action (Enemy sprite1, UpDownPlatform sprite2, int collisionType){
		standardaction (sprite1, sprite2, collisionType);

		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			double sprite1height = sprite1.getHeight();
			sprite1.setY(sprite2.getY()-sprite1height);
		}
	}
	
	public void action (Enemy sprite1, SimplePlatform sprite2, int collisionType){
		standardaction (sprite1, sprite2, collisionType);
		if (collisionType!=CollisionGroup.TOP_BOTTOM_COLLISION)
			sprite1.invertAttribute("OneDirectionMovement");
		if(collisionType == CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite1.allowAttribute("JumpingMovement", false);
		}
	}
	
	

	public void action (Enemy sprite1, BreakablePlatform sprite2, int collisionType){
		standardaction (sprite1, sprite2, collisionType);

		if ((collisionType!=CollisionGroup.TOP_BOTTOM_COLLISION) &&(collisionType!=CollisionGroup.BOTTOM_TOP_COLLISION) )
			sprite1.invertAttribute("OneDirectionMovement");
		if(collisionType == CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite1.allowAttribute("JumpingMovement", false);
			sprite2.doBreak();
		}
	}

	public void action(Sprite sprite1, Sprite sprite2, int collisionType) { 
		
	}

}

