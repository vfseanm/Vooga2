package powerUps;

import java.awt.image.BufferedImage;

import java.util.List;


import attributes.Attribute;

import com.golden.gamedev.Game;

import fighter.attributes.*;


import fighter.Fighter;
import sprite.AnimatedGameSprite;

@SuppressWarnings("serial")
public abstract class PowerUp extends AnimatedGameSprite {

    protected Fighter 	myFighter;
    protected Game 		myGame;

    public PowerUp(BufferedImage[] im, double x, double y, List<String> image, Fighter fighter, Game game) {
        super(im, x, y, image);
        myFighter = fighter;
        myGame = game;
    }
    
    public Fighter getFighter() {
        return myFighter;
    }

    public abstract PowerUp makeItem(double x, double y);
    
    public abstract Attribute getAttribute();
}
