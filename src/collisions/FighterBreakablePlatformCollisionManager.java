package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import platforms.BreakablePlatform;


public class FighterBreakablePlatformCollisionManager extends CollisionGroup {

    @Override
    public void collided(Sprite fighter, Sprite platform) {
        if (getCollisionSide() == BOTTOM_TOP_COLLISION) {
            ((BreakablePlatform) platform).doBehavior(0, 0); //parameters to this don't matter....
        }
    }
}
