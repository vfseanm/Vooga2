package sidescrolling;

import com.golden.gamedev.object.*;


import fighter.Fighter;

public interface Sidescroller {
                 
    public void update(long elapsedTime);
    
    public SpriteGroup[] getSpriteGroups();
    
    public Fighter getFighter();
}
