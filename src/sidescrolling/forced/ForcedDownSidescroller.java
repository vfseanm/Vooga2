package sidescrolling.forced;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;


/**
 * This object allows for forced sidescrolling in the downward direction. The level, along with the fighter
 * will move at a constant speed downward.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class ForcedDownSidescroller extends ForcedSidescroller {

    private double forcedDownSpeed;
    
    /**
     * Creates a new ForcedDownSidescroller.
     * @param scroller - an already created sidescroller
     */
    public ForcedDownSidescroller(Sidescroller scroller) {
        super(scroller);
        double speed = Double.parseDouble(mySidescrollerResources.getString("ForcedDownSpeed"));
        if (speed < 0) {
            throw new RuntimeException(mySidescrollerResources.getString("NegativeSpeedError"));
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
