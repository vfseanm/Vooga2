package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class RotatingPlatform extends AbstractPlatform {

	protected RotatingPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images) {
		super(im, x, y, images);
	}
	
	//TODO: distance needed to conform to abstractPlatform specifications...will try to fix later
	public void doBehavior(double speed, double distance) {
		
	}

}
