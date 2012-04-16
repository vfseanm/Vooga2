package sidescrolling;

import com.golden.gamedev.object.Sprite;

public class ForcedDownSidescroller extends DecoratedSidescroller {

    private double forcedDownSpeed;
    
    public ForcedDownSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed > 0) {
            throw new RuntimeException("You must choose a negative number.");
        }
        forcedDownSpeed = speed;
    }

    public void move(Sprite sprite) {
        sprite.moveY(forcedDownSpeed);
    }
    
}
