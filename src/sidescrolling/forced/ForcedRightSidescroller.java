package sidescrolling.forced;

import java.awt.event.KeyEvent;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

/**
 * This object allows for forced sidescrolling in the right direction. The level, along with the fighter
 * will move at a constant speed to the right.
 * @author Dustin
 *
 */
public class ForcedRightSidescroller extends ForcedSidescroller {

    private double forcedRightSpeed;
    private double leftBorder;
    
    /**
     * Creates a new ForcedRightSidescroller.
     * @param scroller - an already created sidescroller
     * @param speed - the speed at which sidescrolling will occur
     */
    public ForcedRightSidescroller(Sidescroller scroller, double speed) {
        super(scroller);
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
        forcedRightSpeed = speed;
        leftBorder = 0;
    }

    /**
     * Moves a sprite to the right and prevents the fighter from moving off the left side of the screen.
     */
    public void move(Sprite sprite) {
        sprite.moveX(forcedRightSpeed);
        Fighter fighter = getFighter();
        if (getGame().keyDown(KeyEvent.VK_LEFT) && fighter.getX() <= leftBorder) {
            fighter.setX(leftBorder);
        }
    }
    
}
