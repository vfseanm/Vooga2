package playfield;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import sprite.AnimatedGameSprite;
import com.golden.gamedev.object.Sprite;
import enemies.Enemy;
import fighter.Fighter;


/**
 * Singleton Pattern to use for this class instead of passing them around
 * everywhere this class handles the updating and rendering of all sprites
 * 
 * @author Alex
 */
public class SingletonSpriteManager
{
    private List<AnimatedGameSprite> mySprites;
    private List<Enemy> myEnemies;
    private List<AnimatedGameSprite> toAdd;


    /**
     * singleton pattern
     */
    private SingletonSpriteManager ()
    {
        mySprites = new ArrayList<AnimatedGameSprite>();
        myEnemies = new ArrayList<Enemy>();
        toAdd = new ArrayList<AnimatedGameSprite>();

    }

    /**
     * singleton pattern
     */
    private static class SingletonPlayFieldHolder
    {
        public static final SingletonSpriteManager instance =
            new SingletonSpriteManager();
    }


    /**
     * singleton pattern
     */
    public static SingletonSpriteManager getInstance ()
    {
        return SingletonPlayFieldHolder.instance;
    }


    /**
     * adds a sprite to toAdd during an update call toAdd will be added to the
     * mySprites used to prevent concurrent modification
     * 
     * @param sprite a sprite to add
     */
    public void add (Sprite sprite)
    {

        if (sprite instanceof AnimatedGameSprite &&
            !(sprite instanceof Fighter))
        {
            toAdd.add((AnimatedGameSprite) sprite);
        }

    }


    /**
     * renders all my sprites
     * 
     * @param pen the GTGE render
     */
    public void render (Graphics2D pen)
    {

        for (AnimatedGameSprite s : mySprites)
        {
            s.render(pen);
        }
    }


    /**
     * updates all the sprites also adds the toAdd list to mySprites
     * 
     * @param time
     */
    public void update (long time)
    {
        if (toAdd.size() > 0)
        {
            for (AnimatedGameSprite s : toAdd)
            {
                mySprites.add(s);

                if (s instanceof Enemy)
                {
                    myEnemies.add((Enemy) s);
                }

            }
            toAdd.clear();
        }
        for (Sprite s : mySprites)
        {
            s.update(time);
        }
    }


    /**
     * returns all the sprites in mySprites
     * 
     * @return mySprites
     */
    public List<AnimatedGameSprite> getMySprites ()
    {
        return mySprites;
    }


    /**
     * sets mySprites to a given list useful for loading in levels and adding
     * the sprites
     * 
     * @param list
     */
    public void setMySprites (List<AnimatedGameSprite> list)
    {
        mySprites = list;
    }

    /**
     * returns the enemies
     * @return the list of enemies in the game
     */
    public List<Enemy> getMyEnemies ()
    {
        return myEnemies;
    }
}
