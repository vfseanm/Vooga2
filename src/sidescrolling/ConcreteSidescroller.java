package sidescrolling;


import com.golden.gamedev.object.Sprite;


import com.golden.gamedev.object.SpriteGroup;

import fighter.Fighter;

public class ConcreteSidescroller extends Sidescroller {

    private Fighter fighter;
    private SpriteGroup[] myGroups;
    
    public ConcreteSidescroller(Fighter character, SpriteGroup...groups) {
        fighter = character;
        myGroups = groups;
    }

    public void move(Sprite sprite) {
        return;
    }
    
    public SpriteGroup[] getSpriteGroups() {
        return myGroups;
    }
    
    public Fighter getFighter() {
        return fighter;
    }
    
}
