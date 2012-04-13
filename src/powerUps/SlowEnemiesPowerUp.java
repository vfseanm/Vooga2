package powerUps;

import java.awt.image.BufferedImage

;
import java.util.List;

import fighter.attributes.*;

import attributes.Attribute;

import com.golden.gamedev.Game;

import platforms.BreakablePlatformItemFactory;

import fighter.Fighter;

@SuppressWarnings("serial")
public class SlowEnemiesPowerUp extends PowerUp {

    public SlowEnemiesPowerUp(BufferedImage[] im, double x, double y, List<String> image, Fighter fighter, Game game) {
        super(im, x, y, image, fighter, game);
    }
    
    private SlowEnemiesPowerUp(BufferedImage[] im, List<String> image, Fighter fighter, Game game) {
        super(im, 0, 0, image, fighter, game);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage[] im, List<String> image, Fighter fighter, Game game) {
        return new BreakablePlatformItemFactory(new SlowEnemiesPowerUp(im, image, fighter, game));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new SlowEnemiesPowerUp(getImages(), x, y, getImageNames(), getFighter(), myGame);
    }
    
    public Attribute getAttribute() {
        //to be implemented later
        return null;
    }
}
