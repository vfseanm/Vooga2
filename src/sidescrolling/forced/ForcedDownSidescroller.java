package sidescrolling.forced;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;


/**
 * This object allows for forced sidescrolling in the downward direction. The level, along with the fighter
 * will move at a constant speed downward.
 * @author Dustin
 *
 */
public class ForcedDownSidescroller extends ForcedSidescroller {

    private double forcedDownSpeed;
    
    /**
     * Creates a new ForcedDownSidescroller.
     * @param scroller - an already created sidescroller
     * @param speed - the speed at which sidescrolling will occur
     */
    public ForcedDownSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
        forcedDownSpeed = speed;
    }

    /**
     * Moves a sprite down.
     */
    public void move(Sprite sprite) {
        sprite.moveY(forcedDownSpeed);
    }
    
}
