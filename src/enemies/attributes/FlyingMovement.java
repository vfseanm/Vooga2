package enemies.attributes;

public class FlyingMovement extends Attribute implements Updateable
{

    public void update (long elaspedTime)
    {
        // TODO write flying movement

    }


    @Override
    public String getName ()
    {
        return "FlyingMovement";
    }


    //Will update to reflect changes of class
    public String toString ()
    {
        return "Attribute FlyingMovement";
    }

}
