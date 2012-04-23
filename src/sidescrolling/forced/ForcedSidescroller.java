package sidescrolling.forced;

import sidescrolling.DecoratedSidescroller;

import sidescrolling.Sidescroller;

/**
 * A ForcedScroller moves the level along with the fighter at a constant speed in a certain direction.
 * There are four subclasses, one for each direction - up, down, left, and right. The fighter can move 
 * freely on the screen but cannot go anywhere in the level besides what is visible. When the fighter is
 * not being moved by the player, it will move off screen along with the rest of the level.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public abstract class ForcedSidescroller extends DecoratedSidescroller {
    
    /**
     * Creates a new ForcedSidescroller.
     * @param scroller - an already used sidescroller
     */
    public ForcedSidescroller(Sidescroller scroller) {
        super(scroller);
    }
        
    /**
     * Updates this sidescroller by telling all sprites, including the fighter, to move.
     */
    public void update(long elapsedTime) {
        updateSprites();
        move(fighter);
        super.update(elapsedTime);
    }
    
}
