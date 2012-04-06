package collisions;

import powerUps.FireBallPowerUp;
import powerUps.InvincibilityPowerUp;
import powerUps.SlowEnemiesPowerUp;
import powerUps.SpeedUpPowerUp;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class EnemyPowerUpCollisionManager extends CollisionGroup{

	public void collided(Sprite enemy, Sprite powerUp) {
		powerUp.setActive(false);
		if (powerUp.getClass()==FireBallPowerUp.class){

		}

		else if (powerUp.getClass()==InvincibilityPowerUp.class){

		}

		else if (powerUp.getClass()==SlowEnemiesPowerUp.class){

		}

		else if (powerUp.getClass() == SpeedUpPowerUp.class){

		}

	}
}
