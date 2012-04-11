package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class UpSidescroller extends DecoratedSidescroller { 

    private Game myGame;
    
    public UpSidescroller(Game game, Sidescroller scroller) {
        super(scroller);
        myGame = game;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_UP)) {
            sprite.moveY(1.0);
        }
        super.move(sprite);
    }
    
}
