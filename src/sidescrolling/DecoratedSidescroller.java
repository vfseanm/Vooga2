package sidescrolling;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class DecoratedSidescroller implements Sidescroller {

    private Sidescroller wrappedScroller;
    
    public DecoratedSidescroller(Sidescroller scroller) {
        wrappedScroller = scroller;
    }
    
    public void update(long elapsedTime) {
        
        for (SpriteGroup group: getSpriteGroups()) {
            
            for (Sprite object: group.getSprites()) {
                if (object != null) {
                    move(object);
                }
            }
            
        }
    }
    
    public void move(Sprite sprite) {
        wrappedScroller.move(sprite);
    }
    
    public SpriteGroup[] getSpriteGroups() {
        return wrappedScroller.getSpriteGroups();
    }
    
}
