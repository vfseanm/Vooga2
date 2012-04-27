package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

/**
 * This object allows for shifted sidescrolling in the left direction.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class ShiftLeftSidescroller extends ShiftSidescroller {

    /**
     * Creates a new ShiftLeftSidescroller.
     * @param scroller - an already created sidescroller
     */
    public ShiftLeftSidescroller(Sidescroller scroller) {
        super(scroller);
    }
    
    /**
     * moves a sprite to the right by the width of the screen.
     */
    public void move(Sprite sprite) {
        sprite.setX(sprite.getX() + getGameWidth());
    }
    
    public void moveFighter() {
        fighter.setX(getGameWidth() - fighter.getWidth() - 10);
    }
    
    /**
     * @returns true if the fighter moves off the left side of the screen
     */
    public boolean fighterOffCorrectSide() {
        return fighter.getX() <= 0;
    }
    
}
