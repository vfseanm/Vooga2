package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class BasicAllDirections extends SidescrollBehavior {

    public BasicAllDirections(Game game, SpriteGroup... groups) {
        super(game, groups);
    }

    public void sidescroll(Sprite object) {

        if (myGame.keyDown(KeyEvent.VK_LEFT)) {
            object.moveX(2);
        }
        if (myGame.keyDown(KeyEvent.VK_RIGHT)) {
            object.moveX(-2);
        }
        if (myGame.keyDown(KeyEvent.VK_UP)) {
            object.moveY(2);
        }
        if (myGame.keyDown(KeyEvent.VK_DOWN)) {
            object.moveY(-2);
        }

    }

}
