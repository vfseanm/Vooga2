package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

/**
 * This object allows for shifted sidescrolling in the upward direction.
 * @author Dustin
 *
 */
public class ShiftUpSidescroller extends ShiftSidescroller {

    /**
     * Creates a new ShiftUpSidescroller.
     * @param scroller - an already created sidescroller
     */
    public ShiftUpSidescroller(Sidescroller scroller) {
        super(scroller);
    }
    
    /**
     * moves a sprite to the down by the height of the screen.
     */
    public void move(Sprite sprite) {
        sprite.setY(sprite.getY() + getGameHeight());
    }
    
    /**
     * @returns true if the fighter moves off the down side of the screen
     */
    public boolean fighterOffCorrectSide() {
        return fighter.getY() <= 0;
    }
    
}
