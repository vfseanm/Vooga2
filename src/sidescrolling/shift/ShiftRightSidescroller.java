package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.*;

public class ShiftRightSidescroller extends ShiftSidescroller {

    public ShiftRightSidescroller(Sidescroller scroller) {
        super(scroller);
    }
    
    public void move(Sprite sprite) {
        sprite.setX(sprite.getX() - getGame().getWidth());
    }
    
    public boolean fighterOffCorrectSide() {
        return getFighter().getX() >= getGame().getWidth();
    }
    
}
