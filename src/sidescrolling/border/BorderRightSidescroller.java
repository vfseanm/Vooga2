package sidescrolling.border;

import java.awt.event.KeyEvent;


import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

/**
 * This object allows for normal sidescrolling in the right direction. A border will be created that when the 
 * Fighter crosses it from the left and is moving in the right direction, all objects in the level will move left
 * to make it appear as if the Fighter is moving right throguth the level.
 * @author Dustin
 *
 */
public class BorderRightSidescroller extends BorderSidescroller {
    
    private double boundary;
    
    /**
     * Creates a new BorderLeftSidescroller.
     * @param scroller - an already created sidescroller
     * @param offsetFromRight - how far from the right of the screen a border will be created
     */
    public BorderRightSidescroller(Sidescroller scroller, double offsetFromRight) {
        super(scroller);
        boundary = getGame().getWidth() - offsetFromRight - getFighter().getWidth();
    }
    
    /**
     * Moves a sprite to the left to make it appear as if the fighter is moving right when the fighter is on the 
     * right border and the right key is being held down.
     */
    public void move(Sprite sprite) {
        if (getGame().keyDown(KeyEvent.VK_RIGHT)) {
            Fighter fighter = getFighter();
            if (fighter.getX() >= boundary) {
                sprite.moveX(-getFighter().getMovement()[0]);
                fighter.setX(boundary);
            }
        }
    }
    
}
