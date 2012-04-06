package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import sprite.Fighter;

@SuppressWarnings("serial")
public class SimplePlatform extends AbstractPlatform {
  
    
    public SimplePlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter) {
        super(im, x, y, images, fighter);
    }

    public void doBehavior() {
        //do nothing....yay!
    }
    
    public void update(elapsedTime) {
        //do nothing....yay again!! 
    }

}
