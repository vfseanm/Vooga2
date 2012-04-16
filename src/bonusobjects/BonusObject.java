package bonusobjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sprite.AnimatedGameSprite;
import attributes.Attribute;

@SuppressWarnings("serial")
public class BonusObject extends AnimatedGameSprite {

    protected List<Attribute>		myAttributes;
    protected List<Attribute>		myAttributesToOffer;

    public BonusObject(BufferedImage[] im, double x, double y, List<String> image) {
        super(im, x, y, image);
        myAttributes = new ArrayList<Attribute>();
        myAttributesToOffer = new ArrayList<Attribute>();
    }
   
    // returns attributes to be added to sprite that collects this bonus object
    public List<Attribute> getAttributesToOffer() {
    	return Collections.unmodifiableList(myAttributesToOffer);
    }
    
    public void addAttribute(Attribute attributeToAdd) {
    	myAttributes.add(attributeToAdd);
    }
    
    public void addAttributeToOffer(Attribute attributeToAdd) {
    	myAttributesToOffer.add(attributeToAdd);
    }
}