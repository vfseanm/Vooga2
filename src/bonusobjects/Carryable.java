package bonusobjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import attributes.Attribute;

@SuppressWarnings("serial")
public class Carryable extends BonusObject {
	
	public Carryable(BufferedImage[] im, double x, double y,
			List<String> image) {
		super(im, x, y, image);
		myAttributes = new ArrayList<Attribute>();
		myAttributesToOffer = new ArrayList<Attribute>();
	}
}
