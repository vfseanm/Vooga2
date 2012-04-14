package collisions;

import com.golden.gamedev.object.Sprite;
import fighter.Fighter;
import enemies.Enemy;

public class InstantDeathAction implements ActionPerformer{
	
	private void action(Enemy sprite1, Fighter sprite2, int collisionType) {
		sprite2.dies();
	}

	public void action(Sprite sprite1, Sprite sprite2, int collisionType) {
		if ((sprite1 instanceof Enemy) && (sprite2 instanceof Fighter)){
			action ((Enemy)sprite1, (Fighter) sprite2, collisionType);
		}

	}

	
}
