package sidescrolling;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;


import fighter.Fighter;

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
     * @return the list of SpriteGroups in the ConcreteSidescroller
     */
    public SpriteGroup[] getSpriteGroups();
    
    /**
     * 
     * @return the Fighter in the ConcreteSidescroller
     */
    public Fighter getFighter();
    
    /**
     * 
     * @return the Game in the ConcreteSidescroller
     */
    public Game getGame();
}
