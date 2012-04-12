package src.collisions;

import com.golden.gamedev.object.Sprite;
import src.fighter.Fighter;
import src.enemies.Enemy;

public class InstantDeathAction extends ActionPerformer{
	
	public void action(Enemy sprite1, Fighter sprite2, int collisionType) {
		sprite2.dies();
	}
	
	public void action(Sprite sprite1, Sprite sprite2, int collisionType) {}

	
}
