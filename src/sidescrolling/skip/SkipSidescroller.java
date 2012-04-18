package sidescrolling.skip;

import sidescrolling.DecoratedSidescroller;

import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;

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
            updateGroups();
        }
        super.update(elapsedTime);
    }
    
}
