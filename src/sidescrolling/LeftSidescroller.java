package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class LeftSidescroller extends DecoratedSidescroller {

    private Game myGame;
    
    public LeftSidescroller(Game game, Sidescroller scroller) {
        super(scroller);
        myGame = game;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_LEFT)) {
            sprite.moveX(1.0);
        }
        super.move(sprite);
    }
    
    
}
