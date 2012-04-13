package fighter;

import java.awt.image.BufferedImage;
import java.util.List;

import sprite.*;

@SuppressWarnings("serial")
public class FighterDeath extends AnimatedGameSprite {

	public FighterDeath(BufferedImage[] image, double x, double y, List<String> images) {
		super(image, x, y, images);
	}
	
}
