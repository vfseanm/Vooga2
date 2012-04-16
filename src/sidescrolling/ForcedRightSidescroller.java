package sidescrolling;

import com.golden.gamedev.object.Sprite;

public class ForcedRightSidescroller extends ForcedSidescroller {

    private double forcedRightSpeed;
    
    public ForcedRightSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed > 0) {
            throw new RuntimeException("You must choose a negative number.");
        }
        forcedRightSpeed = speed;
    }

    public void move(Sprite sprite) {
        sprite.moveX(forcedRightSpeed);
    }
    
}
