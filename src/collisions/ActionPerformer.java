package collisions;

<<<<<<< HEAD
import platforms.BreakablePlatform;
import platforms.DecoratedPlatform;
import platforms.SimplePlatform;
=======
>>>>>>> 253371ac01ae4cbcfa771cb15b96babb3667a5aa

import com.golden.gamedev.object.Sprite;

import enemies.Enemy;
import fighter.Fighter;


public interface ActionPerformer{
	public void action(Sprite sprite1, Sprite sprite2, int collisionType);
	
}