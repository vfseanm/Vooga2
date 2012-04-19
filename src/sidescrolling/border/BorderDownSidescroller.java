package sidescrolling.border;

import java.awt.event.KeyEvent;

import sidescrolling.Sidescroller;


import fighter.Fighter;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

/**
 * 
 * @author dmadust
 *
 */
public class BorderDownSidescroller extends BorderSidescroller {
    
    private double downSpeed;
    private double boundary;
    
    /**
     * 
     * @param game
     * @param scroller
     * @param speed
     * @param offsetFromBottom
     */
    public BorderDownSidescroller(Game game, Sidescroller scroller, double speed, double offsetFromBottom) {
        super(game, scroller);
        if (speed > 0) {
            throw new RuntimeException("You must choose a negative number.");
        }
        downSpeed = speed;
        boundary = myGame.getHeight() - offsetFromBottom - getFighter().getHeight();
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_DOWN)) {
            Fighter fighter = getFighter();
            if (fighter.getX() >= boundary) {
                sprite.moveX(downSpeed);
                fighter.setX(boundary);
            }
        }
    }
    
}
