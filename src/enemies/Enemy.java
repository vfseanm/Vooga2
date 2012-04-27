package enemies;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import collisions.CollisionAction;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import character.AttributeUser;
import attributes.*;
import attributes.enemyattributes.Flying;
import attributes.enemyattributes.JumpingMovement;
import attributes.enemyattributes.OneDirectionMovement;
import attributes.enemyattributes.PathFollowingMovement;
import attributes.enemyattributes.SideToSideMovement;
import attributes.enemyattributes.UpDownMovement;
import attributes.sharedattributes.Attack;
import attributes.sharedattributes.Gravity;
import attributes.sharedattributes.Hitpoints;
import editor.json.AttributeFactory;
import editor.json.JsonableSprite;
import editor.json.SpriteFactory;
import editor.json.SpriteJsonData;
import enemies.state.EnemyState;


/**
 * The enemy class extends attribute user and implements jsonablesprite
 * 
 * @author Alex
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class Enemy extends AttributeUser implements JsonableSprite
{

    private EnemyState myState;
    private static List<AttributeFactory> myAttributeFactories;
    static
    {
        myAttributeFactories = new ArrayList<AttributeFactory>();
        myAttributeFactories.add(Flying.getFactory());
        myAttributeFactories.add(Hitpoints.getFactory());
        myAttributeFactories.add(Gravity.getFactory());
        myAttributeFactories.add(SideToSideMovement.getFactory());
        myAttributeFactories.add(UpDownMovement.getFactory());
        myAttributeFactories.add(JumpingMovement.getFactory());
        myAttributeFactories.add(PathFollowingMovement.getFactory());
        myAttributeFactories.add(OneDirectionMovement.getFactory());
    }


    /**
     * Constructor used to set location and images
     * 
     * @param x x coordinate of the sprites location
     * @param y y coordinate of the sprites location
     * @param image the image file names which are used to set the images as
     *            well
     */
    public Enemy (double x, double y, List<String> image)
    {
        super(x, y, image);
        setGroup("ENEMY");
    }


    /**
     * called states if they want to cause an enemy to attack otherwise enemies
     * will default attack during update calls
     * 
     * @param elapsedTime the time between GTGE update calls
     */
    public void attack (long elapsedTime)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getClass().getSuperclass().equals(Attack.class)) ((Attack) attribute).update(elapsedTime);

        }

    }


    /**
     * the enemy class overwrites the update in the super class this is because
     * enemystate can influence the behavior of the enemy
     * 
     * @param elapsedTime the time in between GTGE update calls
     */
    public void update (long elapsedTime)
    {

        if (myState != null) myState.excuteBehavior(this, elapsedTime);
        else
        {
            performUpdateableAttributes(elapsedTime);

        }

    }


    /**
     * Sets the state of the enemy
     * 
     * @param state an Enemystate
     */
    public void setState (EnemyState state)
    {
        myState = state;
    }


    /**
     * returns the name of the enemy
     */
    public String getName ()
    {
        return "Enemy";
    }


    /**
     * returns the string represenation of the enemy class
     */
    public String toString ()
    {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(super.toString());
        if (myState != null)
        {
            toReturn.append(myState.toString());
        }
        return toReturn.toString();
    }


    /**
     * clones a given enemy used in the level editor
     */
    public Object clone ()
    {
        List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        Enemy e = new Enemy(this.getX(), this.getY(), imageNames);
        for (Attribute a : myAttributes)
        {
            e.addAttribute((Attribute) a.clone());
        }
        if (myState != null)
        {
            e.setState(myState);
        }
        e.setGroup(this.getGroup());
        return e;
    }


    /**
     * The toJson method creates a json string representation of the enemy class
     */

    public String toJson ()
    {
        Gson gson = new Gson();
        Map<String, String> attributeList = new HashMap<String, String>();
        for (Attribute a : myAttributes)
        {

            attributeList.put(a.getClass().toString(), a.toJson());
        }
        String additionalInformation = gson.toJson(attributeList);

        return gson.toJson(new SpriteJsonData(this, additionalInformation));

    }


    /**
     * returns the correct enemy action class that is used for collisions
     */
    public Class<? extends CollisionAction> getActionClass ()
    {
        return EnemyAction.class;
    }


    /**
     * converts and enemy from json and correctly instantiates the enemy
     */
    public Enemy fromJson (String json)
    {
        Gson gson = new Gson();

        SpriteJsonData spriteData = gson.fromJson(json, SpriteJsonData.class);
        Enemy sprite =
            new Enemy(spriteData.getX(),
                      spriteData.getY(),
                      spriteData.getImageNames());
        sprite.setGroup(spriteData.getGroup());
        Type collectionType = new TypeToken<HashMap<String, String>>()
        {}.getType();
        Map<String, String> attributeMap =
            gson.fromJson(spriteData.getAdditionalInformation(), collectionType);
        for (String attributeClassName : attributeMap.keySet())
        {
            for (AttributeFactory factory : myAttributeFactories)
            {
                if (factory.isThisKindOfAttribute(attributeClassName))
                {
                    sprite.addAttribute(factory.parseFromJson(attributeMap.get(attributeClassName)));
                }
            }

        }
        return sprite;
    }


    /**
     * used by the sprite factory
     */
    private Enemy ()
    {}


    /**
     * calls the private enemy constructor used to determine if the sprite is an
     * enemy
     * 
     * @return a SpriteFactory with a new enemy
     */
    public static SpriteFactory<Enemy> getFactory ()
    {
        return new SpriteFactory<Enemy>(new Enemy());
    }

}
