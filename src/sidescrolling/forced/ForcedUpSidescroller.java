package sidescrolling.forced;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

public class ForcedUpSidescroller extends ForcedSidescroller {

    private double forcedUpSpeed;
    
    public ForcedUpSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
        forcedUpSpeed = speed;
    }

    public void move(Sprite sprite) {
        sprite.moveY(forcedUpSpeed);
    }
    
}