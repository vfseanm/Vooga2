package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import fighter.*;

@SuppressWarnings("serial")
public class SimplePlatform extends AbstractPlatform {
  
    
    public SimplePlatform(BufferedImage[] im, double x, double y, List<String> images, Fighter fighter) {
        super(im, x, y, images, fighter);
    }

    public void doBehavior(double speed, double distance) {
        //do nothing....yay!
    }
    
    public void update(long elapsedTime) {
        //do nothing....yay again!! 
    }
    
    public String toString()
    {
        return "platform";
    }

}
