package sidescrolling;

import java.util.ArrayList;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.Game;

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
     * @return the Game in the ConcreteSidescroller
     */
    public Game getGame();
}
