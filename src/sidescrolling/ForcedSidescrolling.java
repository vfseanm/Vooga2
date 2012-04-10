package sidescrolling;


import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class ForcedSidescrolling extends SidescrollBehavior {
    
    public ForcedSidescrolling(Game game, SpriteGroup...groups) {
        super(game, groups);
    }
    
    public void sidescroll(Sprite object) {
        object.moveX(-0.2);
    }
    
}
