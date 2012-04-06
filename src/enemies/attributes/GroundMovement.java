package enemies.attributes;

public class GroundMovement extends Attribute implements Updateable
{

    public void update (long elaspedTime)
    {
        // TODO write groundmovement
        
    }

    @Override
    public String getName ()
    {
        
        return "GroundMovement";
    }
    
  //Will update to reflect changes of class
    public String toString(){
        return "Attribute GroundMovement";
    }

}
