package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;


public class OneDirectionMovement extends Attribute implements Updateable
{
    private String myDirection;
    private int myDistance;

    @editorConstructor(parameterNames = { "direction", "distance" })
    public OneDirectionMovement (String direction, int distance)
    {
        super(direction,distance);
        myDirection = direction;
        myDistance = distance;
    }


    public void modifyOneDirectionMovement (String direction, int distance)
    {
        myDirection = direction;
        myDistance += distance;
    }

    
    public void update (long elaspedTime)
    {
        if (myEnemy.isOnScreen())
        {
            if (myDirection.equalsIgnoreCase("left"))
            {
                myEnemy.setLocation(myEnemy.getX() - myDistance, myEnemy.getY());
            }
            else if (myDirection.equalsIgnoreCase("right"))
            {
                myEnemy.setLocation(myEnemy.getX() + myDistance, myEnemy.getY());
            }
            else if (myDirection.equalsIgnoreCase("up"))
            {
                myEnemy.setLocation(myEnemy.getX(), myEnemy.getY() - myDistance);
            }
            else if (myDirection.equalsIgnoreCase("down"))
            {
                myEnemy.setLocation(myEnemy.getX(), myEnemy.getY() + myDistance);
            }

        }
    }
    


    @Override
    public String getName ()
    {
        return "OneDirectionMovement";
    }


    public String toString ()
    {
        return "Attribute OneDirectionMovement My Direction is "+myDirection+" my distance is "+myDistance;
    }

}
