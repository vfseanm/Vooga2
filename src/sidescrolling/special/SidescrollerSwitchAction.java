package sidescrolling.special;

import com.golden.gamedev.object.collision.CollisionGroup;
import sprite.AnimatedGameSprite;
import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

public class SidescrollerSwitchAction implements CollisionAction {
    private SidescrollerSwitch sprite;
    
    /**
     * If a platform and fighter collide, this method calls switchSidescroller() from the 
     * SidescrollerSwitch class.
     * @param ccntext - the context of this CollisionAction
     */
    public void switchSidescroller(CollisionContext ccntext, CollisionSpec cspec) {
        if (ccntext.getSide() == CollisionGroup.TOP_BOTTOM_COLLISION) {
            sprite.switchSidescroller();
        }
    }
    
    /**
     * sets the sprite given to this CollisionAction as a SidescrollerSwitch.
     */
    public void setSprite(AnimatedGameSprite sprite) {
        this.sprite = (SidescrollerSwitch) sprite;
    }
    
}
