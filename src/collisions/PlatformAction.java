package collisions;

 
import platforms.platformtypes.*;

 
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;
import fighter.Fighter;


public class PlatformAction implements ActionPerformer{

	public void standardEnemyaction (Enemy sprite1, AbstractPlatform sprite2, int collisionType){ 
		
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			if ( (sprite1.getX() >= (sprite2.getX() - sprite1.getWidth() ))
					&& ((sprite1.getX() + sprite1.getWidth()) <= sprite2.getX()+ sprite2.getWidth() + sprite1.getWidth()) ){
				sprite1.setY(sprite2.getY()-sprite1.getHeight()-1);
				(sprite1).restoreOriginalAttribute("JumpingMovement");	
			}
		}
		if ((collisionType!=CollisionGroup.TOP_BOTTOM_COLLISION) && (collisionType!=CollisionGroup.BOTTOM_TOP_COLLISION))
			sprite1.invertAttribute("OneDirectionMovement");
		 if(collisionType == CollisionGroup.BOTTOM_TOP_COLLISION){
			
			sprite1.allowAttribute("JumpingMovement", false);
		}	
	}
	
	public void action (Enemy sprite1, RotatingPlatform sprite2, int collisionType){
		standardEnemyaction (sprite1, sprite2, collisionType);
	}
	
	public void action (Enemy sprite1, UpDownPlatform sprite2, int collisionType){
		standardEnemyaction (sprite1, sprite2, collisionType);
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			sprite1.setY(sprite2.getY()-sprite1.getHeight()-1);
		}
	}
	
	public void action (Enemy sprite1, SimplePlatform sprite2, int collisionType){
		standardEnemyaction (sprite1, sprite2, collisionType);
		
	}
	
	public void action (Enemy sprite1, SideToSidePlatform sprite2, int collisionType){
		standardEnemyaction (sprite1, sprite2, collisionType);
	}
	
	
	public void action (Enemy sprite1, BreakablePlatform sprite2, int collisionType){
		standardEnemyaction (sprite1, sprite2, collisionType);
		if (collisionType == CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite2.doBreak();
		}
	}

	public void standardFighteraction(Fighter sprite1, AbstractPlatform sprite2, int collisionType){ 
		
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			if ( (sprite1.getX() >= (sprite2.getX() - sprite1.getWidth() ))
					&& ((sprite1.getX() + sprite1.getWidth()) <= sprite2.getX()+ sprite2.getWidth() + sprite1.getWidth()) ){
				sprite1.setY(sprite2.getY()-sprite1.getHeight()-1);
				(sprite1).restoreOriginalAttribute("Jump");	
			}
		}
		if ((collisionType!=CollisionGroup.TOP_BOTTOM_COLLISION) && (collisionType!=CollisionGroup.BOTTOM_TOP_COLLISION))
			sprite1.invertAttribute("OneDirectionMovement");
		 if(collisionType == CollisionGroup.BOTTOM_TOP_COLLISION){
			
			sprite1.allowAttribute("Jump", false);
		}	
	}
	
	public void action (Fighter sprite1, RotatingPlatform sprite2, int collisionType){
		standardFighteraction (sprite1, sprite2, collisionType);
	}
	
	public void action (Fighter sprite1, UpDownPlatform sprite2, int collisionType){
		standardFighteraction (sprite1, sprite2, collisionType);
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			sprite1.setY(sprite2.getY()-sprite1.getHeight()-1);
		}
	}
	
	public void action (Fighter sprite1, SimplePlatform sprite2, int collisionType){
		standardFighteraction (sprite1, sprite2, collisionType);
		
	}
	
	public void action (Fighter sprite1, SideToSidePlatform sprite2, int collisionType){
		standardFighteraction (sprite1, sprite2, collisionType);
	}
	
	
	public void action (Fighter sprite1, BreakablePlatform sprite2, int collisionType){
		standardFighteraction (sprite1, sprite2, collisionType);
		if (collisionType == CollisionGroup.BOTTOM_TOP_COLLISION){
			sprite2.doBreak();
		}
	}

	public void action(Sprite sprite1, Sprite sprite2, int collisionType) {
		// TODO Auto-generated method stub
		
	}

}

