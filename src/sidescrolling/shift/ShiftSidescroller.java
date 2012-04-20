package sidescrolling.shift;

import sidescrolling.DecoratedSidescroller;

import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;

public abstract class ShiftSidescroller extends DecoratedSidescroller {
        
    protected Game myGame;
    
    public ShiftSidescroller(Game game, Sidescroller scroller) {
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
