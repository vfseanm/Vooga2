package attributes.enemyattributes;

import attributes.Attribute;
import attributes.interfaces.Updateable;
import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;


/**
 * Flying attribute which when added to an attribute user the user will not fall
 * due to gravity
 * 
 * @author Alex
 */
@SuppressWarnings("serial")
public class Flying extends Attribute implements Updateable, JsonableAttribute
{
    /**
     * Constructor for flying calls the super constructor of Attribute
     */
    @editorConstructor(parameterNames = { "" })
    public Flying ()
    {
        super();

    }


    /**
     * The update method associated to flying if flying is active gravity can
     * not be active otherwise gravity is restored to its original state
     * 
     * @param elapsedTime the time in between GTGE
     */
    public void update (long elaspedTime)
    {

        if (isActive)
        {
            myAttributeUser.allowAttribute("Gravity", false);
        }
        else
        {
            myAttributeUser.restoreOriginalAttribute("Gravity");
        }
    }

    /**
     * returns the name of the attribute
     */
    @Override
    public String getName ()
    {
        return "Flying";
    }

    /**
     * returns the string representation of flying
     */
    public String toString ()
    {
        return "Attribute Flying is " + isActive;
    }

    /**
     * inherited from the updateable interface 
     * flying can not be inverted
     */
    public void invert ()
    {

    }

    /**
     * Used to clone a flying object
     */
    public Object clone ()
    {
        return new Flying();
    }

    /**
     * converts a string from json to an actual object
     */
    public Flying fromJson (String json)
    {
        return new Flying();
    }

    /**
     * Used in json file converting
     * 
     * @return an attribute factory with a new flying object
     */
    public static AttributeFactory<Flying> getFactory ()
    {
        return new AttributeFactory<Flying>(new Flying());
    }

}
