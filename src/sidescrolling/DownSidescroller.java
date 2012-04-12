package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class DownSidescroller extends DecoratedSidescroller {
    
    private double downSpeed;
    private Game myGame;
    
    public DownSidescroller(Game game, Sidescroller scroller, double speed) {
        super(scroller);
        myGame = game;
        if (speed > 0) {
            speed = 0;
        }
        downSpeed = speed;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_DOWN)) {
            sprite.moveY(downSpeed);
        }
        super.move(sprite);
    }
}
