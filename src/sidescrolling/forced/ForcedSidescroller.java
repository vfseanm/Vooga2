package sidescrolling.forced;

import sidescrolling.DecoratedSidescroller;
import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class ForcedSidescroller extends DecoratedSidescroller {
    
    public ForcedSidescroller(Sidescroller scroller) {
        super(scroller);
    }
        
    public void update(long elapsedTime) {
        for (SpriteGroup group: getSpriteGroups()) {
            for (Sprite object: group.getSprites()) {
                if (object != null) {
                    move(object);
                }
            }      
        }
        move(getFighter());
        super.update(elapsedTime);
    }
    
}
