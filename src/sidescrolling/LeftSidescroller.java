package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Sprite;

public class LeftSidescroller extends DecoratedSidescroller {

    private double leftSpeed;
    private BaseInput inputEngine;
    
    public LeftSidescroller(BaseInput input, Sidescroller scroller, double speed) {
        super(scroller);
        inputEngine = input;
        //can't move right...
        if (speed < 0) {
            speed = 0;
        }
        leftSpeed = speed;
    }
    
    public void move(Sprite sprite) {
        if (inputEngine.isKeyDown(KeyEvent.VK_LEFT)) {
            sprite.moveX(leftSpeed);
        }
        super.move(sprite);
    }
    
    
}
