package sidescrolling.border;

import sidescrolling.DecoratedSidescroller;

import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;

public abstract class BorderSidescroller extends DecoratedSidescroller {

    protected Game myGame;
    
    public BorderSidescroller(Game game, Sidescroller scroller) {
        super(scroller);
        myGame = game;
    }
        
    public void update(long elapsedTime) {
        updateGroups();
        super.update(elapsedTime);
    }
    
}
