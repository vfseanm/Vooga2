package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

/**
 * This object allows for shifted sidescrolling in the downward direction.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class ShiftDownSidescroller extends ShiftSidescroller {

    /**
     * Creates a new ShiftDownSidescroller.
     * @param scroller - an already created sidescroller
     */
    public ShiftDownSidescroller(Sidescroller scroller) {
        super(scroller);
    }
    
    /**
     * moves a sprite up by the height of the game
     */
    public void move(Sprite sprite) {
        sprite.setY(sprite.getY() - getGameHeight());
    }
    
    public void moveFighter() {
        fighter.setY(10);
    }
    
    /**
     * @returns true if the fighter moves off the bottom of the screen
     */
    public boolean fighterOffCorrectSide() {
        return fighter.getY() >= getGameHeight();
    }
    
}
