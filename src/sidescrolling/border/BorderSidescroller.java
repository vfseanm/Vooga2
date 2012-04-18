package sidescrolling.border;

import sidescrolling.DecoratedSidescroller;
import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;

public abstract class BorderSidescroller extends DecoratedSidescroller {

    protected Game myGame;
    
    public BorderSidescroller(Game game, Sidescroller scroller) {
        super(scroller);
        myGame = game;
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
