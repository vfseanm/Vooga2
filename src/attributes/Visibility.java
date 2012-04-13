package attributes;

import java.awt.image.BufferedImage;

/**
 * Experimenting with building a layer ontop of the GTGE methods to consolidate
 * all functions into attributes
 * Here I'm thinking cloaked or ghosts
 * @author Alex
 */
public class Visibility extends Attribute
{
    private boolean isVisible;
    private BufferedImage[] myImage;


    public Visibility (boolean visible)
    {
        isVisible = visible;
        myImage=myEnemy.getImages();
    }


    @Override
    public String getName ()
    {
        return "Visibility";
    }


    public void modifyVisibility (boolean visible)
    {
        isVisible = visible;
        if(!isVisible){
            myEnemy.setImage(arg0);
        }
    }
    
    public String toString(){
        return "Attribute Visibility is currently" + isVisible;
    }

}
