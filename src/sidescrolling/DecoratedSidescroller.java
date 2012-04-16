package sidescrolling;

import com.golden.gamedev.object.Sprite
;

import com.golden.gamedev.object.SpriteGroup;

import fighter.Fighter;

public abstract class DecoratedSidescroller implements Sidescroller {

    private Sidescroller wrappedScroller;
    
    public DecoratedSidescroller(Sidescroller scroller) {
        wrappedScroller = scroller;
    }
    
    public void update(long elapsedTime) {
        wrappedScroller.update(elapsedTime);
    }
    
    public void move(Sprite sprite) {
        wrappedScroller.move(sprite);
    }
    
    public SpriteGroup[] getSpriteGroups() {
        return wrappedScroller.getSpriteGroups();
    }
    
    public Fighter getFighter() {
        return wrappedScroller.getFighter();
    }


        
}
