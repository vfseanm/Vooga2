package collisions;

 
import platforms.platformtypes.*;

 
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;

public class PlatformAction implements ActionPerformer{

	public void standardEnemyaction (Enemy sprite1, AbstractPlatform sprite2, int collisionType){ 
	    
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			if ( (sprite1.getX()+(sprite1.getWidth()) >= sprite2.getX())
					&& (sprite1.getX() <= sprite2.getX()+ sprite2.getWidth()) ){
				sprite1.restoreOriginalAttribute("JumpingMovement");
			}
		}
		else{
			sprite1.invertAttribute("OneDirectionMovement");
		}
		
	}
	public void action (Enemy sprite1, RotatingPlatform sprite2, int collisionType){ 
		standardEnemyaction (sprite1, sprite2, collisionType);

		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			sprite2.doBehavior(1, 100);
 		}

	}
	
	public void action (Enemy sprite1, UpDownPlatform sprite2, int collisionType){
		standardEnemyaction (sprite1, sprite2, collisionType);

		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			sprite2.doBehavior(1, 100);
		}
	}
	
	public void action (Enemy sprite1, SimplePlatform sprite2, int collisionType){
		standardEnemyaction (sprite1, sprite2, collisionType);
	}

	public void action (Enemy sprite1, BreakablePlatform sprite2, int collisionType){
		standardEnemyaction (sprite1, sprite2, collisionType);
		if (collisionType ==  CollisionGroup.BOTTOM_TOP_COLLISION ){
			sprite2.doBreak();
		}
	}

	public void action(Sprite sprite1, Sprite sprite2, int collisionType) { 
		
	}

}

