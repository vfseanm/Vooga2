package bonusobjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import character.GameCharacter;
import attributes.*;

@SuppressWarnings("serial")
public class PowerUp extends BonusObject {
	
	protected GameCharacter			myGameCharacter;
	
	public PowerUp(double x, double y, List<String> image) {
		super(x, y, image);
	}

	public List<Attribute> getAttributesToOffer() {
		return Collections.unmodifiableList(myAttributesToOffer);
	}
	
	public void setGameCharacter(GameCharacter gameCharacter) {
		myGameCharacter = gameCharacter;
	}
	
	   public Object clone()
	    {
	       List<String> imageNames = new ArrayList<String>();
	        imageNames.addAll(this.getImageNames());
	        PowerUp c = new PowerUp(this.getX(), this.getY(),imageNames);
	        c.setGameCharacter(myGameCharacter);
	        for(Attribute a: myAttributes)
	        {
	            c.addAttribute(a);
	        }
	        for(Attribute a: myAttributesToOffer)
	        {
	            c.addAttributeToOffer(a);
	        }
	        return c;
	    }
	
}
