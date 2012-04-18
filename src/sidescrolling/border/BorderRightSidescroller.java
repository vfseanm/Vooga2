package sidescrolling.border;

import java.awt.event.KeyEvent;

import sidescrolling.Sidescroller;



import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

public class BorderRightSidescroller extends BorderSidescroller {
    
    private double rightSpeed;
    private double boundary;
    
    public BorderRightSidescroller(Game game, Sidescroller scroller, double offsetFromRight) {
        super(game, scroller);
        myGame = game;
        rightSpeed = getFighter().getHorizMovement();
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
