package powerUps;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;

import attributes.Attribute;


import fighter.Fighter;
import sprite.GameSprite;

@SuppressWarnings("serial")
public abstract class PowerUp extends GameSprite {

    private Fighter myCharacter;
    protected Game myGame;

    public PowerUp(BufferedImage im, double x, double y, String image, Fighter fighter, Game game) {
        super(im, x, y, image);
        myCharacter = fighter;
        myGame = game;
    }
    
    public Fighter getFighter() {
        return myCharacter;
    }

    public abstract PowerUp makeItem(double x, double y);
    
    public abstract Attribute getAttribute();
}
