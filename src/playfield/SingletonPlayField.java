package playfield;

import java.util.ArrayList;
import java.util.List;
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
    private List<AnimatedGameSprite> 	mySprites;
    private Fighter						myFighter;


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
        
        if (sprite instanceof Fighter) myFighter = (Fighter) sprite;
        else if (sprite instanceof AnimatedGameSprite) {
            mySprites.add((AnimatedGameSprite) sprite);
        }

    }


    public List<AnimatedGameSprite> getMySprites ()
    {
        return mySprites;
    }


    public void setMySprites (List<AnimatedGameSprite> list)
    {
        mySprites = list;
        for (AnimatedGameSprite sprite : mySprites)
        {
            super.add(sprite);
        }
    }
    
    public Fighter getMyFighter() 
    {
    	return myFighter;
    }
}
