package powerUps;

import java.awt.image.BufferedImage;

import sprite.GameSprite;

@SuppressWarnings("serial")
public abstract class PowerUp extends GameSprite {

    public PowerUp (BufferedImage im, double x, double y, String image) {
        super(im, x, y, image);
    }
    
    public abstract void update(long elapsedTime);
}
