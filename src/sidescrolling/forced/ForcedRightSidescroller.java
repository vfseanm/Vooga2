package sidescrolling.forced;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

/**
 * This object allows for forced sidescrolling in the right direction. The level, along with the fighter
 * will move at a constant speed to the right.
 * @author Dustin
 *
 */
public class ForcedRightSidescroller extends ForcedSidescroller {

    private double forcedRightSpeed;
    
    /**
     * Creates a new ForcedRightSidescroller.
     * @param scroller - an already created sidescroller
     * @param speed - the speed at which sidescrolling will occur
     */
    public ForcedRightSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed > 0) {
            throw new RuntimeException("You must choose a negative number.");
        }
        forcedRightSpeed = speed;
    }

    /**
     * Moves a sprite to the right.
     */
    public void move(Sprite sprite) {
        sprite.moveX(forcedRightSpeed);
    }
    
}
