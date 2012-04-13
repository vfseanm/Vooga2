package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Sprite;

public class UpSidescroller extends DecoratedSidescroller { 

    private double upSpeed;
    private BaseInput inputEngine;
    
    public UpSidescroller(BaseInput input, Sidescroller scroller, double speed) {
        super(scroller);
        inputEngine = input;
        if (speed < 0) {
            throw new RuntimeException("You must choose a positive number.");
        }
        upSpeed = speed;
    }
    
    public void move(Sprite sprite) {
        if (inputEngine.isKeyDown(KeyEvent.VK_UP)) {
            sprite.moveY(upSpeed);
        }
        super.move(sprite);
    }
    
}
