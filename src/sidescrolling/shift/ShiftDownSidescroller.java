package sidescrolling.shift;

import sidescrolling.Sidescroller;

import com.golden.gamedev.object.Sprite;

public class ShiftDownSidescroller extends ShiftSidescroller {

    public ShiftDownSidescroller(Sidescroller scroller) {
        super(scroller);
    }
    
    public void move(Sprite sprite) {
        sprite.setY(sprite.getY() - getGame().getHeight());
    }
    
    public boolean fighterOffCorrectSide() {
        return getFighter().getY() >= getGame().getHeight();
    }
    
}
