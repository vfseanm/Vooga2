package sidescrolling;

import com.golden.gamedev.object.Sprite;

public class ForcedDownSidescroller extends DecoratedSidescroller {

    private double forcedDownSpeed;
    
    public ForcedDownSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed > 0) {
            speed = 0;
        }
        forcedDownSpeed = speed;
    }

    public void move(Sprite sprite) {
        sprite.moveY(forcedDownSpeed);
        super.move(sprite);
    }
    
}
