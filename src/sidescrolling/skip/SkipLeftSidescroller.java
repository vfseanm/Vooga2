package sidescrolling.skip;

import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class SkipLeftSidescroller extends SkipSidescroller {

    public SkipLeftSidescroller(Game game, Sidescroller scroller) {
        super(game, scroller);
    }
    
    public boolean fighterOffCorrectSide() {
        return getFighter().getX() <= 0;
    }
    
    public void move(Sprite sprite) {
        sprite.setX(sprite.getX() + myGame.getWidth());
    }
    
}
