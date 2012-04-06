package powerUps;

import java.awt.image.BufferedImage;



import com.golden.gamedev.Game;

import fighter.attributes.*;


import platforms.BreakablePlatformItemFactory;
import fighter.Fighter;

@SuppressWarnings("serial")
public class SuperJumpPowerUp extends PowerUp {

    public SuperJumpPowerUp(BufferedImage im, double x, double y, String image, Fighter fighter, Game game) {
        super(im, x, y, image, fighter, game);
    }
       
    private SuperJumpPowerUp(BufferedImage im, String image, Fighter fighter, Game game) {
        super(im, 0, 0, image, fighter, game);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage im, String image, Fighter fighter, Game game) {
        return new BreakablePlatformItemFactory(new SuperJumpPowerUp(im, image, fighter, game));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new SuperJumpPowerUp(getImage(), x, y, getImageName(), getFighter(), myGame);
    }
    
    public Attribute getAttribute() {
        Attribute found = getFighter().searchAttributes("jump");
        getFighter().setAttributeFalse(found);
        return new SuperJump(myGame, myFighter);
    }
}
