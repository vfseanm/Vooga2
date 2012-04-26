package attributes.enemyattributes;
import attributes.Attribute;
import attributes.interfaces.Updateable;
import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;



// knows gravity too well?
@SuppressWarnings("serial")
public class Flying extends Attribute implements Updateable, JsonableAttribute
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
    
    public Flying fromJson(String json) 
    {
        return new Flying();
    }
    
    
    public static AttributeFactory<Flying> getFactory()
    {
        return new AttributeFactory<Flying>(new Flying());
    }
    



}
