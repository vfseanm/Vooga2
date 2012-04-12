package collisions;

import platforms.BreakablePlatform;
import platforms.SimplePlatform;

import com.golden.gamedev.object.Sprite;
import fighter.Fighter;
import enemies.Enemy;

public class InstantDeathAction implements ActionPerformer{
	
	public void action(Enemy sprite1, Fighter sprite2, int collisionType) {
		sprite2.dies();
	}
	
	public void action(Enemy sprite1, BreakablePlatform sprite2,
			int collisionType) {
		// TODO Auto-generated method stub
		
	}

	public void action(Enemy sprite1, SimplePlatform sprite2, int collisionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action(Sprite sprite1, Sprite sprite2, int collisionType) {
		// TODO Auto-generated method stub
		
	}

	
}
