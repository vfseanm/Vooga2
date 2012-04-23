package sidescrolling.border;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

/**
 * This object allows for normal sidescrolling in the up direction. A border will be created that when the 
 * Fighter crosses it from the bottom and is moving in the upward direction, all objects in the level will move down
 * to make it appear as if the Fighter is moving up throguth the level.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class BorderUpSidescroller extends BorderSidescroller {

    private double boundary;

    /**
     * Creates a new BorderUpSidescroller.
     * @param scroller - an already created sidescroller
     */
    public BorderUpSidescroller(Sidescroller scroller) {
        super(scroller);
        boundary = Double.parseDouble(mySidescrollerResources.getString("OffsetFromTop"));
    }

    /**
     * Moves a sprite down to make it appear as if the fighter is moving up when the fighter is on the
     * top border.
     */
    public void move(Sprite sprite) {
        if (fighter.getY() <= boundary) {
            sprite.moveY(-fighter.getMovement()[1]);
            fighter.setY(boundary);
        }
    }

}
