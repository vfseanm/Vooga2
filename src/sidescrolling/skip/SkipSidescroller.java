package sidescrolling.skip;

import sidescrolling.DecoratedSidescroller;
import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class SkipSidescroller extends DecoratedSidescroller {
    
    protected Game myGame;
    
    public SkipSidescroller(Game game, Sidescroller scroller) {
        super(scroller);
        myGame = game;
    }
        
    public abstract boolean fighterOffCorrectSide();
    
    public void update(long elapsedTime) {
        if (fighterOffCorrectSide()) {
            move(getFighter());
            for (SpriteGroup group: getSpriteGroups()) {
                for (Sprite object: group.getSprites()) {
                    if (object != null) {
                        move(object);
                    }
                }      
            }
        }
        super.update(elapsedTime);
    }
    
}
