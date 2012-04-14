package powerups;
import java.awt.image.BufferedImage;


import java.util.List;
import platforms.BreakablePlatformItemFactory;

@SuppressWarnings("serial")
public class FireBallPowerUp extends PowerUp {

    public FireBallPowerUp(BufferedImage[] im, double x, double y, List<String> image) {
        super(im, x, y, image);
    }

    private FireBallPowerUp(BufferedImage[] im, List<String> image) {
        super(im, 0, 0, image);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage[] im, List<String> image) {
        return new BreakablePlatformItemFactory(new FireBallPowerUp(im, image));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new FireBallPowerUp(getImages(), x, y, getImageNames());
    }

}
