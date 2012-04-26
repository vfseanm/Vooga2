package sidescrolling;

import com.golden.gamedev.engine.BaseInput;

/**
 * This is the most basic type of usable Sidescroller. Does not actually do any sidescrolling but allows
 * for other sidescrollers to access the fighter and the sprites that need to be moved. You must use
 * setSprites to determine which Sprites must be used by the sidescroller.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class ConcreteSidescroller extends Sidescroller  {

    private int myGameWidth;
    private int myGameHeight;
    
    /**
     * Creates a new ConcreteSidescroller.
     * @param width - width of the game.
     * @param height - height of the game.
     * @param sprites - an arrayList of Sprites that the sidescroller must move.
     */
    public ConcreteSidescroller() {
        myGameWidth = Integer.parseInt(mySidescrollerResources.getString("gameWidth"));
        myGameHeight = Integer.parseInt(mySidescrollerResources.getString("gameHeight"));
    }

    /**
     * Does nothing.
     */
    public void update(long elapsedTime) {
        return;
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
    
    public void setUserInput(BaseInput userInput) {
        return;
    }
}
