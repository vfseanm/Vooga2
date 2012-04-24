package sidescrolling.forced;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

/**
 * This object allows for forced sidescrolling in the upward direction. The level, along with the fighter
 * will move at a constant speed upward.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class ForcedUpSidescroller extends ForcedSidescroller {

    private double forcedUpSpeed;
    
    /**
     * Creates a new ForcedUpSidescroller.
     * @param scroller - an already created sidescroller
     */
    public ForcedUpSidescroller(Sidescroller scroller) {
        super(scroller);
        double speed = Double.parseDouble(mySidescrollerResources.getString("ForcedUpSpeed"));
        if (speed > 0) {
            throw new RuntimeException(mySidescrollerResources.getString("PositiveSpeedError"));
        }
        forcedUpSpeed = speed;
    }

    /**
     * Moves a sprite upward.
     */
    public void move(Sprite sprite) {
        sprite.moveY(forcedUpSpeed);
    }
    
}
