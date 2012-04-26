package playfield;

import java.util.ArrayList;
import sprite.AnimatedGameSprite;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import fighter.Fighter;


/**
 * Singleton Pattern to use for playfields instead of passing them around
 * everywhere
 * 
 * @author Alex
 */
public class SingletonPlayField extends PlayField
{
    private ArrayList<AnimatedGameSprite> mySprites;


    private SingletonPlayField ()
    {
        mySprites = new ArrayList<AnimatedGameSprite>();

    }

    private static class SingletonPlayFieldHolder
    {
        public static final SingletonPlayField instance =
            new SingletonPlayField();
    }


    public static SingletonPlayField getInstance ()
    {
        return SingletonPlayFieldHolder.instance;
    }


    public void add (Sprite sprite)
    {
        super.add(sprite);
        if (sprite instanceof AnimatedGameSprite &&
            !(sprite instanceof Fighter))
        {
            mySprites.add((AnimatedGameSprite) sprite);
        }

    }


    public ArrayList<AnimatedGameSprite> getMySprites ()
    {
        return mySprites;
    }

}
