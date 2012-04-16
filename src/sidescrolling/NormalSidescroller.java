package sidescrolling;

import com.golden.gamedev.object.*;

public class NormalSidescroller extends DecoratedSidescroller {

    public NormalSidescroller(Sidescroller scroller) {
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
