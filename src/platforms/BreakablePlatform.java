package platforms;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class BreakablePlatform extends AbstractPlatform {

	protected BreakablePlatform(BufferedImage im, double x, double y, String image) {
		super(im, x, y, image);
	}
	
	//TODO: refused bequest on parameters...will fix later...
	public void doBehavior(double speed, double distance) {
		
	}

	

}
