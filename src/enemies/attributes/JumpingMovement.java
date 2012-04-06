package enemies.attributes;

public class JumpingMovement extends Attribute implements Updateable
{

    public void update (long elaspedTime)
    {
        //TODO write jumping movement

    }


    @Override
    public String getName ()
    {

        return "JumpingMovement";
    }


    //Will update to reflect changes of class
    public String toString ()
    {
        return "Attribute JumpingMovement";
    }

}
