package powerUps;

import java.awt.image.BufferedImage;

import attributes.Attribute;


import platforms.BreakablePlatformItemFactory;

import fighter.Fighter;

@SuppressWarnings("serial")
public class SuperJumpPowerUp extends PowerUp {

    public SuperJumpPowerUp(BufferedImage im, double x, double y, String image, Fighter fighter) {
        super(im, x, y, image, fighter);
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
    
    public Attribute getAttribute() {
        if (getFighter().containsAttribute(Jump.class)) {
            //set to false;
        }
        return new SuperJump();
    }
}
