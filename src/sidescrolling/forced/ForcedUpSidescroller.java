package sidescrolling.forced;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

/**
 * This object allows for forced sidescrolling in the upward direction. The level, along with the fighter
 * will move at a constant speed upward.
 * @author Dustin
 *
 */
public class ForcedUpSidescroller extends ForcedSidescroller {

    private double forcedUpSpeed;
    
    /**
     * Creates a new ForcedUpSidescroller.
     * @param scroller - an already created sidescroller
     * @param speed - the speed at which sidescrolling will occur
     */
    public ForcedUpSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
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
