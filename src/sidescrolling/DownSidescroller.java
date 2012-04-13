package sidescrolling;

import java.awt.event.KeyEvent;


import characters.fighter.Fighter;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class DownSidescroller extends DecoratedSidescroller {
    
    private double downSpeed;
    private Game myGame;
    private double boundary;
    
    public DownSidescroller(Game game, Sidescroller scroller, double speed, double offsetFromBottom) {
        super(scroller);
        myGame = game;
        if (speed > 0) {
            throw new RuntimeException("You must choose a negative number.");
        }
        downSpeed = speed;
        boundary = myGame.getHeight() - offsetFromBottom;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_DOWN)) {
            Fighter fighter = getFighter();
            if (fighter.getX() >= boundary) {
                sprite.moveX(downSpeed);
                fighter.setX(boundary);
            }
        }
        super.move(sprite);
    }
}
