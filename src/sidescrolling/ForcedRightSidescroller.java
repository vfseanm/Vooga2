package sidescrolling;

import com.golden.gamedev.object.Sprite;

public class ForcedRightSidescroller extends DecoratedSidescroller {

    public ForcedRightSidescroller(Sidescroller scroller) {
        super(scroller);
    }

    public void move(Sprite sprite) {
        sprite.moveX(-1.0);
        super.move(sprite);
    }
    
}
