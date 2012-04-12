package src.collisions;

import com.golden.gamedev.object.Sprite;

public abstract class ActionPerformer{
	public abstract void action(Sprite sprite1, Sprite sprite2, int collisionType);
}
