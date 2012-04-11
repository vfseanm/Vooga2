package sidescrolling;

import com.golden.gamedev.object.Sprite;

public class ForcedUpSidescroller extends DecoratedSidescroller {

    public ForcedUpSidescroller(Sidescroller scroller) {
        super(scroller);
    }

    public void move(Sprite sprite) {
        sprite.moveY(1.0);
        super.move(sprite);
    }
    
}
