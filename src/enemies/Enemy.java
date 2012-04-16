package enemies;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import character.GameCharacter;
import attributes.Attribute;
import attributes.Updateable;
import enemies.state.EnemyState;


@SuppressWarnings("serial")
public class Enemy extends GameCharacter
{
    private ArrayList<Attribute> myAttributes;
    private EnemyState myState;


    public Enemy (BufferedImage[] im, double x, double y, List<String> image)
    {
        super(im, x, y, image);
        myAttributes = new ArrayList<Attribute>();
        setGroup("ENEMY");
    }


    public void update (long elapsedTime)
    {

        for (Attribute attribute : myAttributes)
        {

            if (attribute.getClass().getInterfaces().length != 0 &&
                attribute.getClass().getInterfaces()[0].equals(Updateable.class))
            {

                ((Updateable) attribute).update(elapsedTime);

            }
        }
        if (myState != null) myState.excuteBehavior(this);
    }


    public void setState (EnemyState state)
    {
        myState = state;
    }

    public String getName() {
    	return "Enemy";
    }
    
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(nameAndAttributesToString());
        if (myState != null)
        {
            toReturn.append(myState.toString());
        }
        return toReturn.toString();
    }
}
