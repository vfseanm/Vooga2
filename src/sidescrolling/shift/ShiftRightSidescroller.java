package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.*;

/**
 * This object allows for shifted sidescrolling in the upward direction.
 * @author Dustin
 *
 */
public class ShiftRightSidescroller extends ShiftSidescroller {

    /**
     * Creates a new ShiftRightSidescroller.
     * @param scroller - an already created sidescroller
     */
    public ShiftRightSidescroller(Sidescroller scroller) {
        super(scroller);
    }
    
    /**
     * moves a sprite to the left by the width of the screen.
     */
    public void move(Sprite sprite) {
        sprite.setX(sprite.getX() - getGame().getWidth());
    }
    
    /**
     * @returns true if the fighter moves off the right side of the screen
     */
    public boolean fighterOffCorrectSide() {
        return getFighter().getX() >= getGame().getWidth();
    }
    
}
