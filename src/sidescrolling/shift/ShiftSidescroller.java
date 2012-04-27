package sidescrolling.shift;

import sidescrolling.DecoratedSidescroller;


import sidescrolling.Sidescroller;

/**
 * A ShiftSidescroller moves the level only when the fighter moves off the screen in a certain direction.
 * There are four subclasses, one for each direction - up, down, left, and right. The fighter moves freely
 * around the screen and when it moves off in the current direction, the entire next portion of the level
 * "shifts" onto the screen.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public abstract class ShiftSidescroller extends DecoratedSidescroller {
            
    /**
     * Creates a new ShiftSidescroller.
     * @param scroller - an already created sidescroller
     */
    public ShiftSidescroller(Sidescroller scroller) {
        super(scroller);
    }
      
    /**
     * Checks if the fighter is off the side that would cause sidescrolling to occur.
     * @return true if the fighter is off the correct side
     */
    public abstract boolean fighterOffCorrectSide();
    
    public abstract void moveFighter();
    
    /**
     * Updates this sidescroller by moving all sprites appropriately if the fighter moves off the screen
     * in the direction associated with the subclass.
     */
    public void update(long elapsedTime) {
        if (fighterOffCorrectSide()) {
            updateSprites();
            move(fighter);
        }
        super.update(elapsedTime);
    }
    
}
