package sidescrolling;

import java.awt.event.KeyEvent;



import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

public class BorderLeftSidescroller extends BorderSidescroller {

    private double leftSpeed;
    private double boundary;
    
    public BorderLeftSidescroller(Game game, Sidescroller scroller, double speed, double offsetFromLeft) {
        super(game, scroller);
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
        leftSpeed = speed;
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
