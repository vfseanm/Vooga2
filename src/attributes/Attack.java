package attributes;

public class Attack extends Attribute implements Updateable
{
    public Attack(){
        
    }
   
    public void update (long elaspedTime)
    {
        // TODO Auto-generated method stub
        
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
