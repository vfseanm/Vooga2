package collisions;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class EnemyPlatformCollisionManager extends CollisionGroup{

	@Override
	public void collided(Sprite enemy, Sprite platform) {
		if (getCollisionSide() == BOTTOM_TOP_COLLISION) {			
			if ((enemy.getX()+enemy.getWidth()/2 > platform.getX()) && (enemy.getX()+enemy.getWidth()/2 < platform.getX()+platform.getWidth()) ){
				enemy.setY(platform.getY());
			}
			else{
				enemy.setX(platform.getX()+platform.getWidth()/2); 
				enemy.setY(platform.getY());
			}
		}
		else
			revertPosition1();
	}

}
