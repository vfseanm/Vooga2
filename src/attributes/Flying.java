package attributes;
import editor.editorConstructor;

// knows gravity too well?
@SuppressWarnings("serial")
public class Flying extends Attribute implements Updateable
{

    

    @editorConstructor(parameterNames = { "" })
    public Flying ()
    {
        super();
        
    }


    public void update (long elaspedTime)
    {


        if (isActive) {
            myGameCharacter.allowAttribute("Gravity", false);
        }
        else
        {
            myGameCharacter.restoreOriginalAttribute("Gravity");
        }
    }
    
    


    @Override
    public String getName ()
    {
        return "Flying";
    }


    public String toString ()
    {
        return "Attribute Flying is " + isActive;
    }


    public void invert ()
    {
        
        
    }
    
    public Object clone()
    {
        return new Flying();
    }
    
    public static Flying fromJson(String json) 
    {
        return new Flying();
    }
   

}
