package powerUps;

import java.awt.image.BufferedImage;

import sprite.Fighter;
import sprite.GameSprite;

@SuppressWarnings("serial")
public abstract class PowerUp extends GameSprite {

    private Fighter character;

    public PowerUp(BufferedImage im, double x, double y, String image, Fighter fighter) {
        super(im, x, y, image);
        character = fighter;
    }

    public abstract PowerUp makeItem(double x, double y);

    public abstract void update(long elapsedTime);
}
