package sidescrolling;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class SkipUpSidescroller extends SkipSidescroller {

    public SkipUpSidescroller(Game game, Sidescroller scroller) {
        super(game, scroller);
    }
    
    public void move(Sprite sprite) {
        sprite.setY(sprite.getY() + myGame.getHeight());
    }
    
}
