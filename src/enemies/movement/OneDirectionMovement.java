package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;


@SuppressWarnings("serial")
public class OneDirectionMovement extends Attribute implements Updateable
{
    private String myDirection;
    private int myDistance;


    @editorConstructor(parameterNames = { "direction", "distance" })
    public OneDirectionMovement (String direction, int distance)
    {
        super(direction, distance);
        myDirection = direction;
        myDistance = distance;
        isActive = false;
    }


    public void modifyOneDirectionMovement (String direction, int distance)
    {
        myDirection = direction;
        myDistance += distance;
    }


    public void update (long elaspedTime)
    {
        if (myGameCharacter.isOnScreen() || isActive)
        {
            if (myDirection.equalsIgnoreCase("left"))
            {
                myGameCharacter.setX(myGameCharacter.getX() - myDistance);
            }
            else if (myDirection.equalsIgnoreCase("right"))
            {
                myGameCharacter.setX(myGameCharacter.getX() + myDistance);
            }
            else if (myDirection.equalsIgnoreCase("up"))
            {
                myGameCharacter.setY( myGameCharacter.getY() - myDistance);
            }
            else if (myDirection.equalsIgnoreCase("down"))
            {
                myGameCharacter.setY(myGameCharacter.getY() + myDistance);
            }

        }
    }


    public void invert ()
    {
        myDistance = -myDistance;
    }


    @Override
    public String getName ()
    {
        return "OneDirectionMovement";
    }


    public String toString ()
    {
        return "Attribute OneDirectionMovement My Direction is " + myDirection +
               " my distance is " + myDistance;
    }
    
    public Object clone()
    {
        return new OneDirectionMovement(myDirection, myDistance);
    }
}
