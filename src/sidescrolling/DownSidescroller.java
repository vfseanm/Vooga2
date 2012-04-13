package sidescrolling;

import java.awt.event.KeyEvent;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Sprite;

public class DownSidescroller extends DecoratedSidescroller {
    
    private double downSpeed;
    private BaseInput inputEngine;
    
    public DownSidescroller(BaseInput input, Sidescroller scroller, double speed) {
        super(scroller);
        inputEngine = input;
        if (speed > 0) {
            throw new RuntimeException("You must choose a negative number.");
        }
        downSpeed = speed;
    }
    
    public void move(Sprite sprite) {
        if (inputEngine.isKeyDown(KeyEvent.VK_DOWN)) {
            sprite.moveY(downSpeed);
        }
        super.move(sprite);
    }
}
