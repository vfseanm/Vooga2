package sidescrolling.special;

import com.golden.gamedev.object.collision.CollisionGroup;
import sprite.AnimatedGameSprite;
import collisions.CollisionAction;
import collisions.CollisionContext;

public class SidescrollerSwitchAction implements CollisionAction {
    private SidescrollerSwitch sprite;
    
    public void switchSidescroller(CollisionContext ccntext) {
        if (ccntext.getSide() == CollisionGroup.TOP_BOTTOM_COLLISION) {
            sprite.switchSidescroller();
        }
    }
    
    public void setSprite(AnimatedGameSprite sprite) {
        this.sprite = (SidescrollerSwitch) sprite;
    }
    
}
