package sidescrolling.border;

import sidescrolling.Sidescroller;

import fighter.Fighter;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class BorderUpSidescroller extends BorderSidescroller {

    private double upSpeed;
    private double boundary;

    public BorderUpSidescroller(Game game, Sidescroller scroller, double speed,
            double offsetFromTop) {
        super(game, scroller);
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
        upSpeed = speed;
        boundary = offsetFromTop;
    }

    public void move(Sprite sprite) {
        Fighter fighter = getFighter();
        if (fighter.getY() <= boundary) {
            sprite.moveY(upSpeed);
            fighter.setY(boundary);
        }
    }

}
