package sidescrolling;

import java.util.ArrayList;


import sprite.AnimatedGameSprite;

import com.golden.gamedev.Game;

/**
 * This is the most basic type of usable Sidescroller. Does not actually do any sidescrolling but allows
 * for other sidescrollers to access the fighter and the sprites that need to be moved.
 * @author Dustin
 *
 */
public class ConcreteSidescroller implements Sidescroller {

    private ArrayList<AnimatedGameSprite> mySprites;
    private Game myGame;
    
    /**
     * Creates a new ConcreteSidescroller.
     * @param game - the main game.
     * @param sprites - an arrayList of Sprites that the sidescroller must move.
     */
    public ConcreteSidescroller(Game game, ArrayList<AnimatedGameSprite> sprites) {
        myGame = game;
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
     * returns the Game the ConcreteSidescroller holds.
     */
    public Game getGame() {
        return myGame;
    }
    
}
