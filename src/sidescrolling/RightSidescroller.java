package sidescrolling;

import java.awt.event.KeyEvent;



import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

public class RightSidescroller extends NormalSidescroller {
    
    private double rightSpeed;
    private double boundary;
    
    public RightSidescroller(Game game, Sidescroller scroller, double speed, double offsetFromRight) {
        super(game, scroller);
        myGame = game;
        if (speed > 0) {
            throw new RuntimeException("You must choose a negative number.");
        }
        rightSpeed = speed;
        boundary = myGame.getWidth() - offsetFromRight;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_RIGHT)) {
            Fighter fighter = getFighter();
            if (fighter.getX() >= boundary) {
                sprite.moveX(rightSpeed);
                fighter.setX(boundary);
            }
        }
    }
    
}
