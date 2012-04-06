package sprite;


import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;


@SuppressWarnings("serial")
public class GameSprite extends Sprite
{

    private String myType;
    private String myImageName;


    public GameSprite (BufferedImage im, double x, double y, String image)
    {

        super(im, x, y);
        setImage(im);
        myType = this.getClass().toString();
        myImageName = image;
    }
    

    /**
     * Java is implictly calling superclass constructor
     */
    protected GameSprite(){
        
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
