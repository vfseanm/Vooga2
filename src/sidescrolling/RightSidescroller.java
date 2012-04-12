package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class RightSidescroller extends DecoratedSidescroller {
    
    private double rightSpeed;
    private Game myGame;
    
    public RightSidescroller(Game game, Sidescroller scroller, double speed) {
        super(scroller);
        myGame = game;
        if (speed > 0) {
            speed = 0;
        }
        rightSpeed = speed;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_RIGHT)) {
            sprite.moveX(rightSpeed);
        }
        super.move(sprite);
    }
    
}
