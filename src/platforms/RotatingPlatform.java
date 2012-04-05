package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import sprite.Fighter;

@SuppressWarnings("serial")
public class RotatingPlatform extends AbstractPlatform {

	protected RotatingPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter) {
		super(im, x, y, images, fighter);
	}
	
	//TODO: distance needed to conform to abstractPlatform specifications...will try to fix later
	public void doBehavior(double speed, double distance) {
		
	}

}
