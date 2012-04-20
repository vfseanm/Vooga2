package collisions;

import com.golden.gamedev.object.Sprite;


public interface CustomActionPerformer{
	public void customAction(Sprite sprite1, Sprite sprite2, int collisionType);

}