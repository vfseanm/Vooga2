package sidescrolling.border;

import sidescrolling.Sidescroller;

import fighter.Fighter;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

/**
 * This object allows for normal sidescrolling in the up direction. A border will be created that when the 
 * Fighter crosses it from the bottom and is moving in the upward direction, all objects in the level will move down
 * to make it appear as if the Fighter is moving up throguth the level.
 * @author Dustin
 *
 */
public class BorderUpSidescroller extends BorderSidescroller {

    private double upSpeed;
    private double boundary;

    /**
     * Creates a new BorderUpSidescroller.
     * @param game - the main game
     * @param scroller - an already created sidescroller
     * @param speed
     * @param offsetFromTop - how far from the top of the screen a border will be created
     */
    public BorderUpSidescroller(Game game, Sidescroller scroller, double speed, double offsetFromTop) {
        super(game, scroller);
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
        upSpeed = speed;
        boundary = offsetFromTop;
    }

    /**
     * Moves a sprite down to make it appear as if the fighter is moving up when the fighter is on the
     * top border.
     */
    public void move(Sprite sprite) {
        Fighter fighter = getFighter();
        if (fighter.getY() <= boundary) {
            sprite.moveY(upSpeed);
            fighter.setY(boundary);
        }
    }

}
