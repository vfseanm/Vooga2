package attributes;

import editor.editorConstructor;

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
        myGameCharacter.setY(myGameCharacter.getY() + myDistance);

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
