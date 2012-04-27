package playfield;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import sprite.AnimatedGameSprite;
import com.golden.gamedev.object.Sprite;
import fighter.Fighter;


/**
 * Singleton Pattern to use for playfields instead of passing them around
 * everywhere
 * 
 * @author Alex
 */
public class SingletonSpriteManager
{
    private List<AnimatedGameSprite> mySprites;
    private List<AnimatedGameSprite> toAdd;


    private SingletonSpriteManager ()
    {
        mySprites = new ArrayList<AnimatedGameSprite>();
        toAdd = new ArrayList<AnimatedGameSprite>();

    }

    private static class SingletonPlayFieldHolder
    {
        public static final SingletonSpriteManager instance =
            new SingletonSpriteManager();
    }


    public static SingletonSpriteManager getInstance ()
    {
        return SingletonPlayFieldHolder.instance;
    }


    public void add (Sprite sprite)
    {

        if (sprite instanceof AnimatedGameSprite &&
            !(sprite instanceof Fighter))
        {
            toAdd.add((AnimatedGameSprite) sprite);
        }

    }


    public void render (Graphics2D pen)
    {

        for (AnimatedGameSprite s : mySprites)
        {
            s.render(pen);
        }
    }


    public void update (long time)
    {
        if (toAdd.size() > 0)
        {
            for (AnimatedGameSprite s : toAdd)
            {
                mySprites.add(s);

            }
            toAdd.clear();
        }
        for (Sprite s : mySprites)
        {
            s.update(time);
        }
    }


    public List<AnimatedGameSprite> getMySprites ()
    {
        return mySprites;
    }


    public void setMySprites (List<AnimatedGameSprite> list)
    {
        mySprites = list;
    }
}
