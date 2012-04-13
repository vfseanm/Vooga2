package sidescrolling;

import java.awt.event.KeyEvent;

import Tester.Test;

import com.golden.gamedev.object.Sprite;

public class UpSidescroller extends DecoratedSidescroller { 

    private double upSpeed;
    private Test myGame;
    
    public UpSidescroller(Test game, Sidescroller scroller, double speed) {
        super(scroller);
        myGame = game;
        if (speed < 0) {
            speed = 0;
        }
        upSpeed = speed;
    }
    
    public void move(Sprite sprite) {
        if (myGame.keyDown(KeyEvent.VK_UP)) {
            sprite.moveY(upSpeed);
        }
        super.move(sprite);
    }
    
}
