package sidescrolling;

import java.io.Serializable;
import java.util.ArrayList;
import sprite.AnimatedGameSprite;


/**
 * This is the most basic type of usable Sidescroller. Does not actually do any sidescrolling but allows
 * for other sidescrollers to access the fighter and the sprites that need to be moved.
 * @author Dustin
 *
 */
public class ConcreteSidescroller extends Sidescroller  {

    private ArrayList<AnimatedGameSprite> mySprites;
    private int myGameWidth;
    private int myGameHeight;
    
    /**
     * Creates a new ConcreteSidescroller.
     * @param width - width of the game.
     * @param height - height of the game.
     * @param sprites - an arrayList of Sprites that the sidescroller must move.
     */
    public ConcreteSidescroller(int width, int height, ArrayList<AnimatedGameSprite> sprites) {
        myGameWidth = width;
        myGameHeight = height;
        mySprites = sprites;
    }

    /**
     * Does nothing.
     */
    public void update(long elapsedTime) {
        return;
    }
    
    public void setSprites(ArrayList<AnimatedGameSprite> sprites)
    {
        mySprites = sprites;
    }
    
    /**
     * returns the list of AnimatedGameSprites the ConcreteSidescroller holds.
     */
    public ArrayList<AnimatedGameSprite> getSprites() {
        return mySprites;
    }
    
    /**
     * returns the width of the game that the ConcreteSidescroller holds.
     */
    public int getGameWidth() {
        return myGameWidth;
    }
    
    /**
     * returns the height of the game that the ConcreteSidescroller holds.
     */
    public int getGameHeight() {
        return myGameHeight;
    }
    
}
