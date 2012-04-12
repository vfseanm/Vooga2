package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class LeftSidescroller extends DecoratedSidescroller {

    private double leftSpeed;
    private Game myGame;
    
    public LeftSidescroller(Game game, Sidescroller scroller, double speed) {
        super(scroller);
        myGame = game;
        //can't move right...
        if (speed < 0) {
            speed = 0;
        }
        leftSpeed = speed;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_LEFT)) {
            sprite.moveX(leftSpeed);
        }
        super.move(sprite);
    }
    
    
}
