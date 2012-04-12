package sidescrolling;

import com.golden.gamedev.object.Sprite;

public class ForcedRightSidescroller extends DecoratedSidescroller {

    private double forcedRightSpeed;
    
    public ForcedRightSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed > 0) {
            speed = 0;
        }
        forcedRightSpeed = speed;
    }

    public void move(Sprite sprite) {
        sprite.moveX(forcedRightSpeed);
        super.move(sprite);
    }
    
}
