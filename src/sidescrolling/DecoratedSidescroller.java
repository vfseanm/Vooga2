package sidescrolling;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class DecoratedSidescroller extends Sidescroller {

    private Sidescroller wrappedScroller;
    
    public DecoratedSidescroller(Sidescroller scroller) {
        wrappedScroller = scroller;
    }
    
    public void move(Sprite sprite) {
        wrappedScroller.move(sprite);
    }
    
    public SpriteGroup[] getSpriteGroups() {
        return wrappedScroller.getSpriteGroups();
    }
    
}
