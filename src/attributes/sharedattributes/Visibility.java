package attributes.sharedattributes;

import java.awt.image.BufferedImage;

import attributes.Attribute;
import character.AttributeUser;
import com.google.gson.Gson;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;


/**
 * Experimenting with building a layer on top of the GTGE methods to consolidate
 * all functions into attributes Here I'm thinking cloaked or ghosts
 * 
 * @author Alex
 */
@SuppressWarnings("serial")
public class Visibility extends Attribute implements JsonableAttribute
{
    private boolean isVisible;
    private BufferedImage[] myImage;


    public Visibility (boolean visible)
    {
        super(visible);
        isVisible = visible;

    }


    private void checkAndSetVisibility ()
    {
        if (!isVisible)
        {
            myAttributeUser.setImages(null);
        }
        else
        {
            myAttributeUser.setImages(myImage);
        }

    }


    public void setAttributeUser (AttributeUser character)
    {
        myAttributeUser = character;
        myImage = myAttributeUser.getImages();
        checkAndSetVisibility();

    }


    @Override
    public String getName ()
    {
        return "Visibility";
    }


    public void modifyVisibility (boolean visible)
    {
        isVisible = visible;
        checkAndSetVisibility();
    }


    public String toString ()
    {
        return "Attribute Visibility is currently" + isVisible;
    }


    public Object clone ()
    {
        return new Visibility(isVisible);
    }


    public String toJson ()
    {
        Gson gson = new Gson();
        return gson.toJson(isVisible);
    }


    public Visibility fromJson (String json)
    {
        Gson gson = new Gson();
        boolean visible = gson.fromJson(json, boolean.class);
        return new Visibility(visible);
    }


    private Visibility ()
    {}


    public static AttributeFactory<Visibility> getFactory ()
    {
        return new AttributeFactory<Visibility>(new Visibility());
    }

}
