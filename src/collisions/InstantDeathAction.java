package collisions;

import java.lang.reflect.Method;

import platforms.*;
import platforms.platformtypes.DecoratedPlatform;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.object.Sprite;
import fighter.Fighter;
import enemies.Enemy;

public class InstantDeathAction implements ActionPerformer{
	
	public void action(Enemy sprite1, Fighter sprite2, int collisionType) {
	}

	public void action(Sprite sprite1, Sprite sprite2, int collisionType) {

	}

	public void action(Enemy sprite1, DecoratedPlatform sprite2,
			int collisionType) {		
	}
	
}
