package sidescrolling;

import java.util.ArrayList;
import sprite.AnimatedGameSprite;


/**
 * Sidescroller is an interface that allows one to implement objects which allow for sidescrolling.
 * Each sidescroller must implement a method that updates the screen when sidescrolling
 * occurs, gets a list of sprite groups, and gets the fighter.
 * @author Dustin
 *
 */
public interface Sidescroller {
                 
    /**
     * Updates the sidescroller.
     * @param elapsedTime
     */
    public void update(long elapsedTime);
    
    /**
     * @return the list of Sprites in the ConcreteSidescroller
     */
    public ArrayList<AnimatedGameSprite> getSprites();
    
    /**
     * 
     * @return the width of the game stored in the ConcreteSidescroller
     */
    public int getGameWidth();
    
    /**
     * 
     * @return the height of the game stored in the ConcreteSidescroller
     */
    public int getGameHeight();
    
    public void setSprites(ArrayList<AnimatedGameSprite> sprites);
}
