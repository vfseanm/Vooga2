package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class UpSidescroller extends DecoratedSidescroller { 

    private double upSpeed;
    private Game myGame;
    
    public UpSidescroller(Game game, Sidescroller scroller, double speed) {
        super(scroller);
        myGame = game;
        if (speed < 0) {
            speed = 0;
        }
        upSpeed = speed;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_UP)) {
            sprite.moveY(upSpeed);
        }
        super.move(sprite);
    }
    
}
