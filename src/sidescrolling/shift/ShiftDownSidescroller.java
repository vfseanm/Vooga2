package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class ShiftDownSidescroller extends ShiftSidescroller {

    public ShiftDownSidescroller(Game game, Sidescroller scroller) {
        super(game, scroller);
    }
    
    public void move(Sprite sprite) {
        sprite.setY(sprite.getY() - myGame.getHeight());
    }
    
    public boolean fighterOffCorrectSide() {
        return getFighter().getY() >= myGame.getHeight();
    }
    
}
