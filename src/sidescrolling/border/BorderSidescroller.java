package sidescrolling.border;


import sidescrolling.DecoratedSidescroller;
import sidescrolling.Sidescroller;


/**
 * A BorderSidescroller allows for normal sidescrolling. There are four subclasses, one for each direction -
 * up, down, left, and right. In the game, the character will move freely without moving the level in any
 * direction unless the character touches the border created by any of the BorderSidescrollers. If it crosses
 * this border, the level will move in the direction the fighter crossed the border in by moving all object
 * but the Fighter in the opposite direction.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public abstract class BorderSidescroller extends DecoratedSidescroller{
            
    /**
     * Creates a new BorderSidescroller.
     * @param scroller - an already created sidescroller
     */
    public BorderSidescroller(Sidescroller scroller) {
        super(scroller);
    }
     
    /**
     * Updates this sidescroller by telling all sprites to move.
     */
    public void update(long elapsedTime) {
        updateSprites();
        super.update(elapsedTime);
    }
        
}
