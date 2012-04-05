package collisions;

import powerUps.PowerUp;
import sprite.Fighter;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class FighterPowerUpCollisionManager extends AdvanceCollisionGroup {

    public void collided (Sprite fighter, Sprite power) {
        ((Fighter) fighter).addPowerUp((PowerUp) power);
    }
    
}
