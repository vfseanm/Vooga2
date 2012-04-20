package sidescrolling.border;

import sidescrolling.DecoratedSidescroller;


import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;

/**
 * A BorderSidescroller allows for normal sidescrolling. There are four subclasses, one for each direction -
 * up, down, left, and right. In the game, the character will move freely without moving the level in any
 * direction unless the character touches the border created by any of the BorderSidescrollers. If it crosses
 * this border, the level will move in the direction the fighter crossed the border in by moving all object
 * but the Fighter in the opposite direction.
 * @author Dustin
 *
 */
public abstract class BorderSidescroller extends DecoratedSidescroller {

    protected Game myGame;
    
    /**
     * Creates a new BorderSidescroller.
     * @param game - the main game
     * @param scroller - an already created sidescroller
     */
    public BorderSidescroller(Game game, Sidescroller scroller) {
        super(scroller);
        myGame = game;
    }
     
    /**
     * Updates this sidescroller by telling all sprites to move.
     */
    public void update(long elapsedTime) {
        updateGroups();
        super.update(elapsedTime);
    }
        
}
