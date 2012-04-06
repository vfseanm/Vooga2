package sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import com.golden.gamedev.object.AnimatedSprite;


@SuppressWarnings("serial")
public class AnimatedGameSprite extends AnimatedSprite
{

    String myType;
    ArrayList<String> myImageNames;


    public AnimatedGameSprite (BufferedImage[] im,
                               double x,
                               double y,
                               ArrayList<String> images)
    {

        super(im, x, y);
        setImages(im);
        myType = this.getClass().toString();
        myImageNames = images;
    }


    /**
     * Java is implictly calling superclass constructor
     */
    protected AnimatedGameSprite ()
    {

    }


    public String getType ()
    {
        return myType;
    }


    public ArrayList<String> getImageName ()
    {
        return myImageNames;
    }

}
