package powerUps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import attributes.*;

import sprite.AnimatedGameSprite;

@SuppressWarnings("serial")
public class PowerUp extends AnimatedGameSprite {

    protected List<Attribute>		myAttributes;
    protected List<Attribute>		myAttributesToOffer;


    public PowerUp(BufferedImage[] im, double x, double y, List<String> image, List<Attribute> attributes, List<Attribute> attributesToGive) {
        super(im, x, y, image);
        myAttributes = attributes;
        myAttributesToOffer = attributesToGive;
    }

    //public abstract PowerUp makeItem(double x, double y);
    
    //public abstract Attribute getAttribute();
}
