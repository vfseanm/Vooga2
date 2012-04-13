package powerups;

import java.awt.image.BufferedImage;

import java.util.List;

import attributes.Attribute;


import platforms.BreakablePlatformItemFactory;


@SuppressWarnings("serial")
public class SlowEnemiesPowerUp extends PowerUp {

    public SlowEnemiesPowerUp(BufferedImage[] im, double x, double y, List<String> image) {
        super(im, x, y, image);
    }
    
    private SlowEnemiesPowerUp(BufferedImage[] im, List<String> image) {
        super(im, 0, 0, image);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage[] im, List<String> image) {
        return new BreakablePlatformItemFactory(new SlowEnemiesPowerUp(im, image));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new SlowEnemiesPowerUp(getImages(), x, y, getImageNames());
    }
    
    public Attribute getAttribute() {
        //to be implemented later
        return null;
    }
}
