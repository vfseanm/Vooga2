package sidescrolling;

import com.golden.gamedev.object.Sprite;

public class ForcedUpSidescroller extends DecoratedSidescroller {

    private double forcedUpSpeed;
    
    public ForcedUpSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed < 0) {
            speed = 0;
        }
        forcedUpSpeed = 0;
    }

    public void move(Sprite sprite) {
        sprite.moveY(forcedUpSpeed);
        super.move(sprite);
    }
    
}
