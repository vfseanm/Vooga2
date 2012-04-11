package sidescrolling;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class ConcreteSidescroller implements Sidescroller {

    private SpriteGroup[] myGroups;
    
    public ConcreteSidescroller(SpriteGroup...groups) {
        myGroups = groups;
    }
    
    public void update(long elapsedTime) {
        
        for (SpriteGroup group: myGroups) {
            
            for (Sprite object: group.getSprites()) {
                
                if (object != null) {
                    move(object);
                }
            }
        }
    }
    
    public void move(Sprite sprite) {
        return;
    }
    
    public SpriteGroup[] getSpriteGroups() {
        return myGroups;
    }
    
}
