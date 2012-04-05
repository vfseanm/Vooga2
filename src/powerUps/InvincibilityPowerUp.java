package powerUps;

import java.awt.image.BufferedImage;

import platforms.BreakablePlatformItemFactory;

import sprite.Fighter;

@SuppressWarnings("serial")
public class InvincibilityPowerUp extends PowerUp{

    public InvincibilityPowerUp(BufferedImage im, double x, double y, String image, Fighter fighter) {
        super(im, x, y, image, fighter);
    }
    
    public void update(long elapsedTime) {
        
    }
    
    private InvincibilityPowerUp(BufferedImage im, String image, Fighter fighter) {
        super(im, 0, 0, image, fighter);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage im, String image, Fighter fighter) {
        return new BreakablePlatformItemFactory(new InvincibilityPowerUp(im, image, fighter));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new InvincibilityPowerUp(getImage(), x, y, getImageName(), getFighter());
    }
}
