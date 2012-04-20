package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;

public class ShiftRightSidescroller extends ShiftSidescroller {

    public ShiftRightSidescroller(Game game, Sidescroller scroller) {
        super(game, scroller);
    }
    
    public void move(Sprite sprite) {
        sprite.setX(sprite.getX() - myGame.getWidth());
    }
    
    public boolean fighterOffCorrectSide() {
        return getFighter().getX() >= myGame.getWidth();
    }
    
}
