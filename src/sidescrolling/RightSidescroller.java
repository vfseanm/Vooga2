package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class RightSidescroller extends DecoratedSidescroller {
    
    private Game myGame;
    private double rightSpeed;
    
    public RightSidescroller(Game game, Sidescroller scroller, double speed) {
        super(game, scroller);
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
