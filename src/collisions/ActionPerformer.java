package collisions;

import platforms.BreakablePlatform;
import platforms.SimplePlatform;

import com.golden.gamedev.object.Sprite;


public interface ActionPerformer{
	public void action(Sprite sprite1, Sprite sprite2, int collisionType);
}