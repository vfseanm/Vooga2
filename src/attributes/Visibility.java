package attributes;

import java.awt.image.BufferedImage;
import editor.editorConstructor;

/**
 * Experimenting with building a layer on top of the GTGE methods to consolidate
 * all functions into attributes
 * Here I'm thinking cloaked or ghosts
 * @author Alex
 */
@SuppressWarnings("serial")
public class Visibility extends Attribute
{
    private boolean isVisible;
    private BufferedImage[] myImage;

    @editorConstructor(parameterNames = { "visibility (ghosts)" })
    public Visibility (boolean visible)
    {
        super(visible);
        isVisible = visible;
        myImage = myGameCharacter.getImages();
        checkAndSetVisibility();
    }


    private void checkAndSetVisibility ()
    {
        if(!isVisible){
            myGameCharacter.setImages(null);
        }
        else{
            myGameCharacter.setImages(myImage);
        }
        
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
    
    public String toString(){
        return "Attribute Visibility is currently" + isVisible;
    }

}
