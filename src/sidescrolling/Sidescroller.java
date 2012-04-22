package sidescrolling;

import java.io.Serializable;
import java.util.ArrayList;
import sprite.AnimatedGameSprite;


/**
 * Sidescroller is an interface that allows one to implement objects which allow for sidescrolling.
 * Each sidescroller must implement a method that updates the screen when sidescrolling
 * occurs, gets a list of sprite groups, and gets the fighter.
 * @author Dustin
 *
 */
public abstract class Sidescroller implements Serializable  {
                 
    /**
     * Updates the sidescroller.
     * @param elapsedTime
     */
    public abstract void update(long elapsedTime);
    
    /**
     * @return the list of Sprites in the ConcreteSidescroller
     */
    public abstract ArrayList<AnimatedGameSprite> getSprites();
    
    /**
     * 
     * @return the width of the game stored in the ConcreteSidescroller
     */
    public abstract int getGameWidth();
    
    /**
     * 
     * @return the height of the game stored in the ConcreteSidescroller
     */
    public abstract int getGameHeight();
    
    public abstract void setSprites(ArrayList<AnimatedGameSprite> sprites);
}
