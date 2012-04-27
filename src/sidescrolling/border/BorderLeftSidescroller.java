package sidescrolling.border;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

import demo.SingletonKeyController;

/**
 * This object allows for normal sidescrolling in the left direction. A border will be created that when the 
 * Fighter crosses it from the right and is moving in the left direction, all objects in the level will move right
 * to make it appear as if the Fighter is moving left throguth the level.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class BorderLeftSidescroller extends BorderSidescroller {

    private double boundary;
    
    /**
     * Creates a new BorderLeftSidescroller.
     * @param scroller - an already created sidescroller
     */
    public BorderLeftSidescroller(Sidescroller scroller) {
        super(scroller);
        boundary = Double.parseDouble(mySidescrollerResources.getString("OffsetFromLeft"));
    }
   
    /**
     * Moves a sprite to the right to make it appear as if the fighter is moving left when the fighter is on the
     * left border.
     */
    public void move(Sprite sprite) {


        int leftKey = SingletonKeyController.getInstance().getKeyCode(("LEFT"));
        if (myUserInput.isKeyDown(leftKey) && fighter.getX() <= boundary) {
            sprite.moveX(-fighter.getMovement()[0]);
            fighter.setX(boundary);
        }
    }
        
}
