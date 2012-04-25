package sidescrolling.special;

import sprite.AnimatedGameSprite;
import collisions.CollisionAction;
import collisions.CollisionContext;
import collisions.CollisionSpec;

public class SidescrollerSwitchAction implements CollisionAction {
    private SidescrollerSwitch sprite;
    
    public void switchSidescroller(CollisionContext ccntext, CollisionSpec cspec) {
        sprite.switchSidescroller();
    }
    
    public void setSprite(AnimatedGameSprite sprite) {
        this.sprite = (SidescrollerSwitch) sprite;
    }
    
}
