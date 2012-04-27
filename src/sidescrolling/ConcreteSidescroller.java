package sidescrolling;

import com.golden.gamedev.engine.BaseInput;

/**
 * This is the most basic type of usable Sidescroller. Does not actually do any sidescrolling but allows
 * for other sidescrollers to access the game width and height. Must be used as the inner-most wrapped
 * sidescroller.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class ConcreteSidescroller extends Sidescroller  {

    private int myGameWidth;
    private int myGameHeight;
    
    /**
     * Creates a new ConcreteSidescroller. Gets the height and width of the game from a resource bundle.
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
    
    /**
     * Does nothing.
     */
    public void setUserInput(BaseInput userInput) {
        return;
    }

}
