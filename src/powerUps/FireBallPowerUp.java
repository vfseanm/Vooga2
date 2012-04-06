package powerUps;

import java.awt.image.BufferedImage;

import platforms.BreakablePlatformItemFactory;
import sprite.Fighter;

@SuppressWarnings("serial")
public class FireBallPowerUp extends PowerUp {

    public FireBallPowerUp(BufferedImage im, double x, double y, String image, Fighter fighter) {
        super(im, x, y, image, fighter);
    }

    private FireBallPowerUp(BufferedImage im, String image, Fighter fighter) {
        super(im, 0, 0, image, fighter);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage im, String image, Fighter fighter) {
        return new BreakablePlatformItemFactory(new FireBallPowerUp(im, image, fighter));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new FireBallPowerUp(getImage(), x, y, getImageName(), getFighter());
    }

}
