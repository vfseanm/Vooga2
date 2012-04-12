package enemies.movement;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;

public class Gravity extends Attribute implements Updateable
{
    private int myDistance;
    private int gravity;

    @editorConstructor(parameterNames = { "distance" })
    public Gravity (int distance)
    {
        myDistance = distance;
        gravity = distance;
    }


    public void modifyGravityDistance (int distance)
    {
        myDistance = distance;

    }


    // to reset gravity
    public void modifyGravity ()
    {
        resetGravity();
    }


    public void update (long elaspedTime)
    {
        myEnemy.setY(myEnemy.getY() + myDistance);

    }


    private void resetGravity ()
    {
        myDistance = gravity;
    }


    @Override
    public String getName ()
    {
        return "Gravity";
    }


    public String toString ()
    {
        return "Attribute Gravity my distance is " + myDistance;
    }

}
