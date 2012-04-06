package powerUps;

import java.awt.image.BufferedImage;

import platforms.BreakablePlatformItemFactory;

import sprite.Fighter;

@SuppressWarnings("serial")
public class SuperJumpPowerUp extends PowerUp {

    public SuperJumpPowerUp(BufferedImage im, double x, double y, String image, Fighter fighter) {
        super(im, x, y, image, fighter);
    }
    
    public void update(long elapsedTime) {
        
    }
    
    private SuperJumpPowerUp(BufferedImage im, String image, Fighter fighter) {
        super(im, 0, 0, image, fighter);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage im, String image, Fighter fighter) {
        return new BreakablePlatformItemFactory(new SuperJumpPowerUp(im, image, fighter));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new SuperJumpPowerUp(getImage(), x, y, getImageName(), getFighter());
    }
}