package sidescrolling;

import java.awt.event.KeyEvent;


import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Sprite;

public class RightSidescroller extends DecoratedSidescroller {
    
    private double rightSpeed;
    private BaseInput inputEngine;
    
    public RightSidescroller(BaseInput input, Sidescroller scroller, double speed) {
        super(scroller);
        inputEngine = input;
        if (speed > 0) {
            throw new RuntimeException("You must choose a negative number.");
        }
        rightSpeed = speed;
    }
    
    public void move(Sprite sprite) {
        if (inputEngine.isKeyDown(KeyEvent.VK_RIGHT)) {
            sprite.moveX(rightSpeed);
        }
        super.move(sprite);
    }
    
}
