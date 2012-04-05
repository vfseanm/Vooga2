package platforms;

import java.awt.image.BufferedImage;

import sprite.GameSprite;


@SuppressWarnings("serial")
public class Platform extends GameSprite {
    
    public Platform (BufferedImage im, double x, double y, String image) {
        super(im, x, y, image);
    }

}
