package sidescrolling;

import java.util.ArrayList;


import sprite.AnimatedGameSprite;

import com.golden.gamedev.Game;

import fighter.Fighter;

/**
 * This is the most basic type of usable Sidescroller. Does not actually do any sidescrolling but allows
 * for other sidescrollers to access the fighter and the sprites that need to be moved.
 * @author Dustin
 *
 */
public class ConcreteSidescroller implements Sidescroller {

    private Fighter myFighter;
    private ArrayList<AnimatedGameSprite> mySprites;
    private Game myGame;
    
    /**
     * Creates a new ConcreteSidescroller.
     * @param game - the main game.
     * @param character - the Fighter that the sidescroller uses.
     * @param sprites - an arrayList of Sprites that the sidescroller must move.
     */
    public ConcreteSidescroller(Game game, Fighter character, ArrayList<AnimatedGameSprite> sprites) {
        myGame = game;
        myFighter = character;
        mySprites = sprites;
    }

    /**
     * Does nothing.
     */
    public void update(long elapsedTime) {
        return;
    }
    
    /**
     * returns the list of AnimatedGameSprites the ConcreteSidescroller holds.
     */
    public ArrayList<AnimatedGameSprite> getSprites() {
        return mySprites;
    }
    
    /**
     * returns the Fighter the ConcreteSidescroller holds.
     */
    public Fighter getFighter() {
        return myFighter;
    }
    
    /**
     * returns the Game the ConcreteSidescroller holds.
     */
    public Game getGame() {
        return myGame;
    }
    
}
