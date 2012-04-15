package powerUps;

import java.awt.image.BufferedImage;

import java.util.List;
import attributes.*;
import platforms.BreakablePlatformItemFactory;

@SuppressWarnings("serial")
public class SuperJumpPowerUp extends PowerUp {

    public SuperJumpPowerUp(BufferedImage[] im, double x, double y, List<String> image) {
        super(im, x, y, image);
    }
       
    private SuperJumpPowerUp(BufferedImage[] im, List<String> image) {
        super(im, 0, 0, image);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage[] im, List<String> image) {
        return new BreakablePlatformItemFactory(new SuperJumpPowerUp(im, image));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new SuperJumpPowerUp(getImages(), x, y, getImageNames());
    }
    
    public Attribute getAttribute() {
    	return null;
    }
}
