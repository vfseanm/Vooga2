package sidescrolling.border;

import java.awt.event.KeyEvent;

import sidescrolling.Sidescroller;



import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

/**
 * This object allows for normal sidescrolling in the left direction. A border will be created that when the 
 * Fighter crosses it from the right and is moving in the left direction, all objects in the level will move right
 * to make it appear as if the Fighter is moving left throguth the level.
 * @author Dustin
 *
 */
public class BorderLeftSidescroller extends BorderSidescroller {

    private double leftSpeed;
    private double boundary;
    
    /**
     * Creates a new BorderLeftSidescroller.
     * @param game - the main game
     * @param scroller - an already created sidescroller
     * @param offsetFromLeft - how far from the left of the screen a border will be created
     */
    public BorderLeftSidescroller(Game game, Sidescroller scroller, double offsetFromLeft) {
        super(game, scroller);
        leftSpeed = getFighter().getHorizMovement();
        boundary = offsetFromLeft;
    }
   
    /**
     * Moves a sprite to the right to make it appear as if the fighter is moving left when the fighter is on the
     * left border and the left key is being held down.
     */
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_LEFT)) {
            Fighter fighter = getFighter();
            if (fighter.getX() <= boundary) {
                sprite.moveX(leftSpeed);
                fighter.setX(boundary);
            }
        }
    }
        
}
