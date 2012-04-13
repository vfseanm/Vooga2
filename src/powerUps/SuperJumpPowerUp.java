package powerUps;

import java.awt.image.BufferedImage;
import java.util.List;



import attributes.Attribute;
import attributes.SuperJump;

import com.golden.gamedev.Game;

import fighter.attributes.*;


import platforms.BreakablePlatformItemFactory;
import fighter.Fighter;

@SuppressWarnings("serial")
public class SuperJumpPowerUp extends PowerUp {

    public SuperJumpPowerUp(BufferedImage[] im, double x, double y, List<String> image, Fighter fighter, Game game) {
        super(im, x, y, image, fighter, game);
    }
       
    private SuperJumpPowerUp(BufferedImage[] im, List<String> image, Fighter fighter, Game game) {
        super(im, 0, 0, image, fighter, game);
    }
    
    public static BreakablePlatformItemFactory getFactory(BufferedImage[] im, List<String> image, Fighter fighter, Game game) {
        return new BreakablePlatformItemFactory(new SuperJumpPowerUp(im, image, fighter, game));
    }
    
    public PowerUp makeItem(double x, double y) {
        return new SuperJumpPowerUp(getImages(), x, y, getImageNames(), getFighter(), myGame);
    }
    
    public Attribute getAttribute() {
        Attribute found = getFighter().searchAttributes("jump");
        getFighter().setAttributeFalse(found);
        return new SuperJump(myGame, myFighter);
    }
}
