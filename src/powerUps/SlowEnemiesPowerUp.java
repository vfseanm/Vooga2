package powerUps;

import java.awt.image.BufferedImage
;

import platforms.BreakablePlatformItemFactory;

import fighter.Fighter;

@SuppressWarnings("serial")
public class SlowEnemiesPowerUp extends PowerUp {

    public SlowEnemiesPowerUp(BufferedImage im, double x, double y, String image, Fighter fighter) {
        super(im, x, y, image, fighter);
    }
    
    private SlowEnemiesPowerUp(BufferedImage im, String image, Fighter fighter) {
        super(im, 0, 0, image, fighter);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage im, String image, Fighter fighter) {
        return new BreakablePlatformItemFactory(new SlowEnemiesPowerUp(im, image, fighter));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new SlowEnemiesPowerUp(getImage(), x, y, getImageName(), getFighter());
    }
}
