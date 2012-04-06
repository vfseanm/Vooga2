package platforms;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import sprite.Fighter;

@SuppressWarnings("serial")
public class SideToSidePlatform extends PlatformDecorator {
	
	FrameTimer myTimer = new FrameTimer();

	public SideToSidePlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter) {
		super(im, x, y, images, fighter);
	}
	
	public void doBehavior(double speed, double distance) {
		double time = (distance * 5)/speed;
		if (myTimer.getElapsedTime() % (time*2) == 0) {
			setHorizontalSpeed(speed/25);
		}
		else if (myTimer.getElapsedTime() % time == 0) {
			setHorizontalSpeed(-speed/25);
		}
		myTimer.update();
	}	                                
}
