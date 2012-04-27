package sidescrolling;


import java.util.ArrayList;
import java.util.ResourceBundle;

import java.util.List;

import playfield.SingletonSpriteManager;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.*;
import fighter.Fighter;

/**
 * DecoratorSidescroller allows the user to "wrap" sidescrollers so that
 * multiple sidescrollers can be used at once. Many of its methods call
 * the method of the same name on the object it is wrapping so that when the
 * wrapping sidescroller is done doing its responsibility the wrapped
 * sidescroller can start its job. In essence, for the most part,
 * DecoratorSidescroller helps tell the next sidescroller to start doing its
 * part.
 * 
 * @author Dustin
 * 
 */
@SuppressWarnings("serial")
public abstract class DecoratedSidescroller extends Sidescroller  {

    transient protected ResourceBundle myKeysResources = ResourceBundle
            .getBundle("demo.GameKeysResourceBundle");
    private Sidescroller wrappedScroller;
    protected Fighter fighter;
    private SingletonSpriteManager mySpriteManager;
    protected BaseInput myUserInput;
    
    /**
     * Creates a new DecoratedSidescroller
     * 
     * @param scroller - an already created sidescroller
     */
    public DecoratedSidescroller(Sidescroller scroller) {
        wrappedScroller = scroller;
        fighter = Fighter.getInstance();
        mySpriteManager = SingletonSpriteManager.getInstance();
    }

    /**
     * Calls update on the wrapped sidescroller.
     */
    public void update(long elapsedTime) {
        wrappedScroller.update(elapsedTime);
    }

    /**
     * Gets the width of the game from the ConcreteSidescroller
     */
    public int getGameWidth() {
        return wrappedScroller.getGameWidth();
    }
    
    /**
     * Gets the height of the game from the ConcreteSidescroller
     */
    public int getGameHeight() {
        return wrappedScroller.getGameHeight();
    }

    /**
     * Moves every Sprite.
     */
    public void updateSprites() {
        for (Sprite object: mySpriteManager.getMySprites()) {
            if (object != null) {
                move(object);
            }
        }
    }
    
    /**
     * Sets BaseInput for this sidescroller and all wrapped ones.
     */
    public void setUserInput(BaseInput userInput) {
        myUserInput = userInput;
        wrappedScroller.setUserInput(userInput);
    }

    /**
     * Moves a sprite a certain way depending on the sidescroller being used.
     * 
     * @param sprite - the sprite that is being moved
     */
    public abstract void move(Sprite sprite);
    
    @SuppressWarnings("rawtypes")
	public List<Class> getClassesOfDecorators()
    {
        List<Class> classList = new ArrayList<Class>();
        classList.add(this.getClass());
        if(!wrappedScroller.getClass().equals(ConcreteSidescroller.class))
        {
            classList.addAll(((DecoratedSidescroller) wrappedScroller).getClassesOfDecorators());
        }
        return classList;       
    }

}
