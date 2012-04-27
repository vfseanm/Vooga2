package sidescrolling.border;
 
import sidescrolling.Sidescroller;
import com.golden.gamedev.object.Sprite;

import demo.SingletonKeyController;


/**
 * This object allows for normal sidescrolling in the right direction. A border will be created that when the 
 * Fighter crosses it from the left and is moving in the right direction, all objects in the level will move left
 * to make it appear as if the Fighter is moving right throguth the level.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class BorderRightSidescroller extends BorderSidescroller {
    
    private double boundary;
    
    /**
     * Creates a new BorderLeftSidescroller.
     * @param scroller - an already created sidescroller
     */
    public BorderRightSidescroller(Sidescroller scroller) {
        super(scroller);
        boundary = getGameWidth() - Double.parseDouble(mySidescrollerResources.getString("OffsetFromRight")) 
                - fighter.getWidth();
    }
    
    /**
     * Moves a sprite to the left to make it appear as if the fighter is moving right when the fighter is on the 
     * right border and the right key is being held down.
     */
    public void move(Sprite sprite) {
        int rightKey = SingletonKeyController.getInstance().getKeyCode(("RIGHT"));
        if (myUserInput.isKeyDown(rightKey) && fighter.getX() >= boundary) {
            sprite.moveX(-fighter.getMovement()[0]);
            fighter.setX(boundary);
        }
    }
    
}
