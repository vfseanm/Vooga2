package attributes;

import java.awt.image.BufferedImage;

/**
 * Experimenting with building a layer ontop of the GTGE methods to consolidate
 * all functions into attributes
 * Here I'm thinking cloaked or ghosts
 * @author Alex
 */
@SuppressWarnings("serial")
public class Visibility extends Attribute
{
    private boolean isVisible;
    private BufferedImage[] myImage;


    public Visibility (boolean visible)
    {
        super(visible);
        isVisible = visible;
        myImage=myEnemy.getImages();
        checkAndSetVisibility();
    }


    private void checkAndSetVisibility ()
    {
        if(!isVisible){
            myEnemy.setImages(null);
        }
        else{
            myEnemy.setImages(myImage);
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
