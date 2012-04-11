package sidescrolling;

import com.golden.gamedev.object.Sprite;

public class ForcedDownSidescroller extends DecoratedSidescroller {

    public ForcedDownSidescroller(Sidescroller scroller) {
        super(scroller);
    }

    public void move(Sprite sprite) {
        sprite.moveY(-1.0);
        super.move(sprite);
    }
    
}
