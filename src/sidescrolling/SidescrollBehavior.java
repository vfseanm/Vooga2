package sidescrolling;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class SidescrollBehavior {

    protected Game myGame;
    protected SpriteGroup[] myGroups;
    
    public SidescrollBehavior(Game game, SpriteGroup...groups) {
        myGame = game;
        myGroups = groups;
    }
    
    public abstract void sidescroll(Sprite object);
    
    public void update(long elapsedTime) {
        for (SpriteGroup group: myGroups) {
            
            for (Sprite object: group.getSprites()) {
                
                if (object != null) {
                    sidescroll(object);
                }
                
            }
        }
    }
    
}
