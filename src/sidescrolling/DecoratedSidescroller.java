package sidescrolling;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class DecoratedSidescroller extends Sidescroller {

    protected Game myGame;
    private Sidescroller wrappedScroller;
    
    public DecoratedSidescroller(Game game, Sidescroller scroller) {
        myGame = game;
        wrappedScroller = scroller;
    }
    
    public void move(Sprite sprite) {
        wrappedScroller.move(sprite);
    }
    
    public SpriteGroup[] getSpriteGroups() {
        return wrappedScroller.getSpriteGroups();
    }
    
}
