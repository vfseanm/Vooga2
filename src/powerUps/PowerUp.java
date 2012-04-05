package powerUps;

import java.awt.image.BufferedImage;

import sprite.GameSprite;

@SuppressWarnings("serial")
public class PowerUp extends GameSprite {

    public PowerUp (BufferedImage im, double x, double y, String image) {
        super(im, x, y, image);
    }
}
