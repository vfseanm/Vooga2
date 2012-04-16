package collisions;


import com.golden.gamedev.object.Sprite;


public interface ActionPerformer{
	public void action(Sprite sprite1, Sprite sprite2, int collisionType);
}