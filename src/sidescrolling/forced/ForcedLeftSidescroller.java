package sidescrolling.forced;

import sidescrolling.Sidescroller;
import com.golden.gamedev.object.Sprite;


/**
 * This object allows for forced sidescrolling in the left direction. The level, along with the fighter
 * will move at a constant speed to the left.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class ForcedLeftSidescroller extends ForcedSidescroller {

    private double forcedLeftSpeed;
    private double rightBorder;
    
    /**
     * Creates a new ForcedLeftSidescroller.
     * @param scroller - an already created sidescroller
     */
    public ForcedLeftSidescroller(Sidescroller scroller) {
        super(scroller);
        double speed = Double.parseDouble(mySidescrollerResources.getString("ForcedLeftSpeed"));
        if (speed > 0) {
            throw new RuntimeException(mySidescrollerResources.getString("PositiveSpeedError"));
        }
        forcedLeftSpeed = speed;
        rightBorder = getGameWidth();
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
