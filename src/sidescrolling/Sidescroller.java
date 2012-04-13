package sidescrolling;

import com.golden.gamedev.object.*;

import characters.fighter.Fighter;

public abstract class Sidescroller {
                 
    public void update(long elapsedTime) {
        for (SpriteGroup group: getSpriteGroups()) {
            for (Sprite object: group.getSprites()) {
                if (object != null) {
                    move(object);
                }
            }      
        }
    }
    
    public abstract void move(Sprite sprite);

    public abstract SpriteGroup[] getSpriteGroups();
    
    public abstract Fighter getFighter();
}
