package powerups;

import java.awt.image.BufferedImage;

import java.util.List;
import com.golden.gamedev.Game;
import fighter.Fighter;
import platforms.BreakablePlatformItemFactory;


@SuppressWarnings("serial")
public class InvincibilityPowerUp extends PowerUp{

    public InvincibilityPowerUp(BufferedImage[] im, double x, double y, List<String> image) {
        super(im, x, y, image);
    }
    
    private InvincibilityPowerUp(BufferedImage[] im, List<String> image) {
        super(im, 0, 0, image);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage[] im, List<String> image, Fighter fighter, Game game) {
        return new BreakablePlatformItemFactory(new InvincibilityPowerUp(im, image));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new InvincibilityPowerUp(getImages(), x, y, getImageNames());
    }
    
}
