package powerUps;

import java.awt.image.BufferedImage;
import java.util.List;


import attributes.*;
import fighter.*;
import demo.*;
import com.golden.gamedev.Game;
import sprite.AnimatedGameSprite;

@SuppressWarnings("serial")
public abstract class PowerUp extends AnimatedGameSprite {

    protected Fighter 			myFighter;
    protected PlatformGame 		myGame;

    public PowerUp(BufferedImage[] im, double x, double y, List<String> image, Fighter fighter, PlatformGame game) {
        super(im, x, y, image);
        myFighter = game.getFighter();
        myGame = game;
    }
    
    public Fighter getFighter() {
        return myFighter;
    }

    public abstract PowerUp makeItem(double x, double y);
    
    public abstract Attribute getAttribute();
}
