package weapons.fighterweapons;

import sprite.AnimatedGameSprite;
import collisions.CollisionContext;
import collisions.CollisionSpec;

public class FighterMissileAction {
	
	AnimatedGameSprite myMissile;
	
	public void missileDestruction (CollisionContext ccntext, CollisionSpec cspec){ 
		myMissile.setLocation(-10000, -1000);
		myMissile.setActive(false);
	}
	
	public void setSprite(AnimatedGameSprite sprite) {
		myMissile = sprite;
	}
}
