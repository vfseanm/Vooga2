package sidescrolling.skip;

import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;

public class SkipRightSidescroller extends SkipSidescroller {

    public SkipRightSidescroller(Game game, Sidescroller scroller) {
        super(game, scroller);
    }
    
    public void move(Sprite sprite) {
        sprite.setX(sprite.getX() - myGame.getWidth());
    }
    
    public boolean fighterOffCorrectSide() {
        return getFighter().getX() >= myGame.getWidth();
    }
    
}
