package sidescrolling;

import com.golden.gamedev.object.*;

import fighter.Fighter;

public abstract class DecoratedSidescroller implements Sidescroller {

    private Sidescroller wrappedScroller;
    
    public DecoratedSidescroller(Sidescroller scroller) {
        wrappedScroller = scroller;
    }
    
    public void update(long elapsedTime) {
        wrappedScroller.update(elapsedTime);
    }
    
    public SpriteGroup[] getSpriteGroups() {
        return wrappedScroller.getSpriteGroups();
    }
    
    public Fighter getFighter() {
        return wrappedScroller.getFighter();
    }
    
    public void updateGroups() {
        for (SpriteGroup group: getSpriteGroups()) {
            for (Sprite object: group.getSprites()) {
                if (object != null) {
                    move(object);
                }
            }      
        }
    }

    public abstract void move(Sprite sprite);
        
}
