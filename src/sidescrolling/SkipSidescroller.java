package sidescrolling;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class SkipSidescroller extends DecoratedSidescroller {
    
    public SkipSidescroller(Sidescroller scroller) {
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
        super.update(elapsedTime);
    }
    
}
