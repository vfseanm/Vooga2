package attributes.sharedattributes;

import attributes.Attribute;
import attributes.interfaces.Updateable;

@SuppressWarnings("serial")
public abstract class Attack extends Attribute implements Updateable
{
    public Attack(Object...o){
        super(o);
    }
    
    public void update (long elaspedTime)
    {
        attack();
    }
    
    public abstract void attack();

    @Override
    public String getName ()
    {
        
        return "Attack";
    }
    
    public String toString(){
        return "Attribute Attack";
    }

    public abstract void invert ();
    
    public abstract Object clone();
    
    
   
    
}
