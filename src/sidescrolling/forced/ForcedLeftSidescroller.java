package sidescrolling.forced;

import sidescrolling.Sidescroller;
import com.golden.gamedev.object.Sprite;


/**
 * This object allows for forced sidescrolling in the left direction. The level, along with the fighter
 * will move at a constant speed to the left.
 * @author Dustin
 *
 */
public class ForcedLeftSidescroller extends ForcedSidescroller {

    private double forcedLeftSpeed;
    private double rightBorder;
    
    /**
     * Creates a new ForcedLeftSidescroller.
     * @param scroller - an already created sidescroller
     * @param speed - the speed at which sidescrolling will occur
     */
    public ForcedLeftSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed > 0) {
            throw new RuntimeException("You must choose a negative number.");
        }
        forcedLeftSpeed = speed;
        rightBorder = getGame().getWidth();
    }

    /**
     * Moves a sprite to the left and prevents the fighter from moving off the right side of the screen.
     */
    public void move(Sprite sprite) {
        sprite.moveX(forcedLeftSpeed);
        if (fighter.getX() >= rightBorder - fighter.getWidth()) {
            fighter.setX(rightBorder - fighter.getWidth());
        }
    }
    
}
