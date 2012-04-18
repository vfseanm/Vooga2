package sidescrolling.border;

import java.awt.event.KeyEvent;

import sidescrolling.Sidescroller;



import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

public class BorderLeftSidescroller extends BorderSidescroller {

    private double leftSpeed;
    private double boundary;
    
    public BorderLeftSidescroller(Game game, Sidescroller scroller, double offsetFromLeft) {
        super(game, scroller);
        leftSpeed = -getFighter().getHorizMovement();

        boundary = offsetFromLeft;
    }
   
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
