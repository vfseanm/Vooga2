package sprite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;


@SuppressWarnings("serial")
public class GameSprite extends Sprite
{

    String myType;
    String myImageName;


    protected GameSprite (BufferedImage im, double x, double y, String image)
    {

        super(im, x, y);
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
        setImage(im);
        myType = this.getClass().toString();
        myImageName = image;
    }


    public String getType ()
    {
        return myType;
    }


    public String getImageName ()
    {
        return myImageName;
    }

}