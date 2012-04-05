package platforms;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class SideToSidePlatform extends AbstractPlatform {
	
	FrameTimer myTimer = new FrameTimer();

	public SideToSidePlatform(BufferedImage im, double x, double y, String image) {
		super(im, x, y, image);
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
