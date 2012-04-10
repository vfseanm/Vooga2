package sprite;

import java.awt.image.BufferedImage;

import java.io.Serializable;
import java.util.List;

import com.golden.gamedev.object.sprite.AdvanceSprite;


@SuppressWarnings("serial")
public class AnimatedGameSprite extends AdvanceSprite implements Serializable
{

    String myType;
    List<String> myImageNames;


    public AnimatedGameSprite (BufferedImage[] im,
                               double x,
                               double y,
                               List<String> images)
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


    public List<String> getImageNames ()
    {
        return myImageNames;
    }

}
