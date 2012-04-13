package sidescrolling;

import java.awt.event.KeyEvent;


import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

public class LeftSidescroller extends DecoratedSidescroller {

    private double leftSpeed;
    private Game myGame;
    private double boundary;
    
    public LeftSidescroller(Game game, Sidescroller scroller, double speed, double border) {
        super(scroller);
        myGame = game;
        //can't move right...
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
        leftSpeed = speed;
        boundary = myGame.getWidth() + border;
    }
   
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_LEFT)) {
            Fighter fighter = getFighter();
            if (fighter.getX() <= boundary) {
                sprite.moveX(leftSpeed);
                fighter.setX(boundary);
            }
        }
        super.move(sprite);
    }
    
    
}
