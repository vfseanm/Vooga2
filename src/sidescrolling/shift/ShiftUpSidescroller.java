package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

public class ShiftUpSidescroller extends ShiftSidescroller {

    public ShiftUpSidescroller(Sidescroller scroller) {
        super(scroller);
    }
    
    public void move(Sprite sprite) {
        sprite.setY(sprite.getY() + getGame().getHeight());
    }
    
    public boolean fighterOffCorrectSide() {
        return getFighter().getY() <= 0;
    }
    
}
