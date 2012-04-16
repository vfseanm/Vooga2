package powerups;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import character.GameCharacter;
import attributes.*;

import sprite.AnimatedGameSprite;

@SuppressWarnings("serial")
public abstract class PowerUp extends AnimatedGameSprite {

    protected List<Attribute>		myAttributes;
    protected List<Attribute>		myAttributesToOffer;
    protected GameCharacter			myOwner;


    public PowerUp(BufferedImage[] im, double x, double y, List<String> image) {
        super(im, x, y, image);
        myAttributes = new ArrayList<Attribute>();
        myAttributesToOffer = new ArrayList<Attribute>();
    }

    public abstract PowerUp makeItem(double x, double y);
   
    public List<Attribute> getAttributesToOffer() {
    	return Collections.unmodifiableList(myAttributesToOffer);
    }
    
    public void setOwner(GameCharacter owner) {
    	myOwner = owner;
    }

    //public abstract Attribute getAttribute();
}
