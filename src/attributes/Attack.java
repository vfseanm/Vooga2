package attributes;

@SuppressWarnings("serial")
public class Attack extends Attribute implements Updateable
{
    public Attack(){
        super();
    }
   
    public void update (long elaspedTime)
    {
        
    }

    @Override
    public String getName ()
    {
        
        return "Attack";
    }
    
    public String toString(){
        return "Attribute Attack";
    }

}
