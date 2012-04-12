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

	public void standardaction (Enemy sprite1, AbstractPlatform sprite2, int collisionType){ 
		if (collisionType == CollisionGroup.RIGHT_LEFT_COLLISION){
			if ( (sprite1.getX()+(sprite1.getWidth()/2) >= sprite2.getX())
					&& (sprite1.getX()+(sprite1.getWidth()/2) <= sprite2.getX()+ sprite2.getWidth()) ){
				sprite2.setY(sprite1.getY() + sprite1.getHeight());
				//Gravity is 0? Because you should be able to jump when you're on platform...
			}
		}
	}
	public void action (Enemy sprite1, DecoratedPlatform sprite2, int collisionType){ 

	}

	public void action (Enemy sprite1, SimplePlatform sprite2, int collisionType){
		standardaction (sprite1, sprite2, collisionType);
	}

	public void action (Enemy sprite1, BreakablePlatform sprite2, int collisionType){
		standardaction (sprite1, sprite2, collisionType);
		if (collisionType ==  CollisionGroup.BOTTOM_TOP_COLLISION ){
			for (int i=0; i<=2; i++){
				//Nothing happens...set delay
			}
			sprite2.setActive(false);
		}
	}

	public void action(Sprite sprite1, Sprite sprite2, int collisionType) { 
		if ((sprite1 instanceof Enemy) && (sprite2 instanceof SimplePlatform)){
			action ((Enemy)sprite1, (SimplePlatform) sprite2, collisionType);
		}
		else if ((sprite1 instanceof Enemy) && (sprite2 instanceof BreakablePlatform)){
			action ((Enemy)sprite1, (BreakablePlatform) sprite2, collisionType);
		}

	}

}

