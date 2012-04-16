package sidescrolling;

import java.awt.event.KeyEvent;

import fighter.Fighter;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class UpSidescroller extends NormalSidescroller { 

    private double upSpeed;
    private Game myGame;
    private double boundary;
    
    public UpSidescroller(Game game, Sidescroller scroller, double speed, double offsetFromTop) {
        super(scroller);
        myGame = game;
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
        upSpeed = speed;
        boundary = offsetFromTop;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_UP)) {
            Fighter fighter = getFighter();
            if (fighter.getX() <= boundary) {
                sprite.moveX(upSpeed);
                fighter.setX(boundary);
            }
        }
    }
    
}
