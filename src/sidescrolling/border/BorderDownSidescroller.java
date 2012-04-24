package sidescrolling.border;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

/**
 * This object allows for normal sidescrolling in the downward direction. A
 * border will be created that when the Fighter crosses it from above and is
 * moving in the downward direction, all objects in the level will move up to
 * make it appear as if the Fighter is moving down throguth the level. The
 * sidescroller is functinonal mainly when the Fighter is falling.
 * 
 * @author Dustin
 * 
 */
@SuppressWarnings("serial")
public class BorderDownSidescroller extends BorderSidescroller {

    private double boundary;

    /**
     * Creates a new BorderDownSidescroller.
     * 
     * @param scroller - an already create sidescroller
     */
    public BorderDownSidescroller(Sidescroller scroller) {
        super(scroller);
        boundary = getGameHeight() - Double.parseDouble(mySidescrollerResources
                .getString("OffsetFromBottom")) - fighter.getHeight();
    }

    /**
     * Moves a sprite up to make it appear as if the fighter is moving down when
     * the fighter is on the bottom border.
     */
    public void move(Sprite sprite) {
        if (fighter.getY() >= boundary) {
            sprite.moveY(-fighter.getMovement()[1]);
            fighter.setY(boundary);
        }
    }

}
