package powerUps;

import java.awt.image.BufferedImage;

import fighter.attributes.*;

import com.golden.gamedev.Game;


import platforms.BreakablePlatformItemFactory;
import fighter.Fighter;

@SuppressWarnings("serial")
public class FireBallPowerUp extends PowerUp {

    public FireBallPowerUp(BufferedImage im, double x, double y, String image, Fighter fighter, Game game) {
        super(im, x, y, image, fighter, game);
    }

    private FireBallPowerUp(BufferedImage im, String image, Fighter fighter, Game game) {
        super(im, 0, 0, image, fighter, game);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage im, String image, Fighter fighter, Game game) {
        return new BreakablePlatformItemFactory(new FireBallPowerUp(im, image, fighter, game));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new FireBallPowerUp(getImage(), x, y, getImageName(), getFighter(), myGame);
    }

    public Attribute getAttribute() {
        //to be implemented later
        return null;
    }

}
