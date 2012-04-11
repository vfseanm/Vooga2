package sidescrolling;

import com.golden.gamedev.object.*;

public interface Sidescroller {
         
    public void update(long elapsedTime);
        
    public void move(Sprite sprite);

    public SpriteGroup[] getSpriteGroups();
}
