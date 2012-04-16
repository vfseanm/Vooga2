package sidescrolling;


import com.golden.gamedev.object.Sprite;


import com.golden.gamedev.object.SpriteGroup;

import fighter.Fighter;

public class ConcreteSidescroller implements Sidescroller {

    private Fighter fighter;
    private SpriteGroup[] myGroups;
    
    public ConcreteSidescroller(Fighter character, SpriteGroup...groups) {
        fighter = character;
        myGroups = groups;
    }

    public void update(long elapsedTime) {
        return;
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
