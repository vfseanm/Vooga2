package sprite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;


@SuppressWarnings("serial")
public class AnimatedGameSprite extends AnimatedSprite  {

    String myType;
    ArrayList<String> myImageNames;


    protected AnimatedGameSprite (BufferedImage[] im, double x, double y, ArrayList<String> images) {

        super(im, x, y);
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
        setImages(im);
        myType = this.getClass().toString();
        myImageNames = images;
    }


    public String getType () {
        return myType;
    }


    public ArrayList<String> getImageName () {
        return myImageNames;
    }

}