package sidescrolling;

import java.util.ArrayList;


import sprite.AnimatedGameSprite;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;

/**
 * DecoratorSidescroller allows the user to "wrap" sidescrollers so that
 * multiple sidescrollers can be used at once. The majority of its methods call
 * the method of the same name on the object it is wrapping so that when the
 * wrapping sidescroller is done doing its responsibility the wrapped
 * sidescroller can start its job. In essence, for the most part,
 * DecoratorSidescroller helps tell the next sidescroller to start doing its
 * part.
 * 
 * @author Dustin
 * 
 */
public abstract class DecoratedSidescroller implements Sidescroller {

    private Sidescroller wrappedScroller;

    /**
     * Creates a new DecoratedSidescroller
     * 
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
     * Gets the list of Sprites from the ConcreteSidescroller
     */
    public ArrayList<AnimatedGameSprite> getSprites() {
        return wrappedScroller.getSprites();
    }

    /**
     * Gets the Game from the ConcreteSidescroller
     */
    public Game getGame() {
        return wrappedScroller.getGame();
    }

    /**
     * Moves every Sprite.
     */
    public void updateSprites() {
        for (Sprite object: this.getSprites()) {
            if (object != null) {
                move(object);
            }
        }
    }

    /**
     * Moves a sprite a certain way depending on the sidescroller being used.
     * 
     * @param sprite - the sprite that is being moved
     */
    public abstract void move(Sprite sprite);

}
