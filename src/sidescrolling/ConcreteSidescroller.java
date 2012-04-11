package sidescrolling;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class ConcreteSidescroller extends Sidescroller {

    private SpriteGroup[] myGroups;
    
    public ConcreteSidescroller(SpriteGroup...groups) {
        myGroups = groups;
    }
    
    public void move(Sprite sprite) {
        return;
    }
    
    public SpriteGroup[] getSpriteGroups() {
        return myGroups;
    }
    
}
