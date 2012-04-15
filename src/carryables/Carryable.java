package carryables;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import attributes.Attribute;
import powerUps.*;

@SuppressWarnings("serial")
public class Carryable extends PowerUp {
	
	public Carryable(BufferedImage[] im, double x, double y,
			List<String> image) {
		super(im, x, y, image);
		myAttributes = new ArrayList<Attribute>();
		myAttributesToOffer = new ArrayList<Attribute>();
	}

	@Override
	public Attribute getAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PowerUp makeItem(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

}
