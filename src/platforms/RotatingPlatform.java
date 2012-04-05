package platforms;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class RotatingPlatform extends AbstractPlatform {

	protected RotatingPlatform(BufferedImage im, double x, double y, String image) {
		super(im, x, y, image);
	}
	
	//TODO: distance needed to conform to abstractPlatform specifications...will try to fix later
	public void doBehavior(double speed, double distance) {
		
	}

}
