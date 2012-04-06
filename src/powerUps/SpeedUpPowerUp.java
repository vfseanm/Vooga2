package powerUps;

import java.awt.image.BufferedImage;

import platforms.BreakablePlatformItemFactory;

import fighter.Fighter;

@SuppressWarnings("serial")
public class SpeedUpPowerUp extends PowerUp {

    public SpeedUpPowerUp(BufferedImage im, double x, double y, String image, Fighter fighter) {
        super(im, x, y, image, fighter);
    }
    
    private SpeedUpPowerUp(BufferedImage im, String image, Fighter fighter) {
        super(im, 0, 0, image, fighter);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage im, String image, Fighter fighter) {
        return new BreakablePlatformItemFactory(new SpeedUpPowerUp(im, image, fighter));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new SpeedUpPowerUp(getImage(), x, y, getImageName(), getFighter());
    }
}
