package collisions;

import platforms.BreakablePlatform;
import platforms.DecoratedPlatform;
import platforms.SimplePlatform;

import com.golden.gamedev.object.Sprite;

import enemies.Enemy;
import fighter.Fighter;


public interface ActionPerformer{
	public void action(Sprite sprite1, Sprite sprite2, int collisionType);
	
}