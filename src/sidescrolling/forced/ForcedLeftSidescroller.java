package sidescrolling.forced;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

public class ForcedLeftSidescroller extends ForcedSidescroller {

    private double forcedLeftSpeed;
    
    public ForcedLeftSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
    }

    public void move(Sprite sprite) {
        sprite.moveX(forcedLeftSpeed);
    }
    
}