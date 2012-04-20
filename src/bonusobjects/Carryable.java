package bonusobjects;

import java.util.ArrayList;
import java.util.List;

import attributes.Attribute;
import fighter.Fighter;

@SuppressWarnings("serial")
public class Carryable extends BonusObject {
	
	protected Fighter		myFighter;
	
	public Carryable(double x, double y,
			List<String> image) {
		super(x, y, image);
	}
	
	public void setFighter(Fighter fighter) {
		myFighter = fighter;
	}
	
	public Object clone()
	{
	    List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
	    Carryable c = new Carryable(this.getX(), this.getY(),imageNames);
	    c.setFighter(myFighter);
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
