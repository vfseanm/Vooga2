package sidescrolling;

import com.golden.gamedev.object.*;

import fighter.Fighter;

/**
 * DecoratorSidescroller allows the user to "wrap" sidescrollers so that multiple sidescrollers can
 * be used at once. The majority of its methods call the method of the same name on the object it is
 * wrapping so that when the wrapping sidescroller is done doing its responsibility the wrapped sidescroller
 * can start its job. In essence, for the most part, DecoratorSidescroller helps tell the next sidescroller to
 * start doing its part.
 * @author Dustin
 *
 */
public abstract class DecoratedSidescroller implements Sidescroller {

    private Sidescroller wrappedScroller;
    
    /**
     * Creates a new DecoratedSidescroller
     * @param scroller - an already created sidescroller
     */
    public DecoratedSidescroller(Sidescroller scroller) {
        wrappedScroller = scroller;
    }
    
    /**
     * Calls update on the wrapped sidescroller.
     */
    public void update(long elapsedTime) {
        wrappedScroller.update(elapsedTime);
    }
    
    /**
     * Gets the list of SpriteGroups from the ConcreteSidescroller
     */
    public SpriteGroup[] getSpriteGroups() {
        return wrappedScroller.getSpriteGroups();
    }
    
    /**
     * Gets the Fighter from the ConcreteSidescroller
     */
    public Fighter getFighter() {
        return wrappedScroller.getFighter();
    }
    
    /**
     * Moves every sprite of every sprite group.
     */
    public void updateGroups() {
        for (SpriteGroup group: getSpriteGroups()) {
            for (Sprite object: group.getSprites()) {
                if (object != null) {
                    move(object);
                }
            }      
        }
    }

    /**
     * Moves a sprite a certain way depending on the sidescroller being used.
     * @param sprite - the sprite that is being moved
     */
    public abstract void move(Sprite sprite);
        
}
