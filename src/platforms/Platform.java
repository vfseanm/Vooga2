package platforms;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;
import sprite.AnimatedGameSprite;


//TODO: DELETE ENTIRE CLASS ... only here so that demo game works at the moment....
@SuppressWarnings("serial")
public class Platform extends AnimatedGameSprite {
    
    public Platform (ArrayList<AbstractPlatform> platformWrappers, BufferedImage[] im, double x, double y, List<String> image) {
        super(im, x, y, image);
    }

}
