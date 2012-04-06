package platforms;

import java.awt.image.BufferedImage;

import sprite.GameSprite;

//TODO: DELETE ENTIRE CLASS ... only here so that demo game works at the moment....
@SuppressWarnings("serial")
public class Platform extends GameSprite {
    
    public Platform (BufferedImage im, double x, double y, String image) {
        super(im, x, y, image);
    }

}
