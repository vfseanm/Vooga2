package fighter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import attributes.Attribute;
import sprite.*;

@SuppressWarnings("serial")
public class Missile extends AnimatedGameSprite {

	public Missile(BufferedImage[] image, double x, double y, List<String> images) {
		super(image, x, y, images);
	}
	
}
