package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class ShiftUpSidescroller extends ShiftSidescroller {

    public ShiftUpSidescroller(Game game, Sidescroller scroller) {
        super(game, scroller);
    }
    
    public void move(Sprite sprite) {
        sprite.setY(sprite.getY() + myGame.getHeight());
    }
    
    public boolean fighterOffCorrectSide() {
        return getFighter().getY() <= 0;
    }
    
}
