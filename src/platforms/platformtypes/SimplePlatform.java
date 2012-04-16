package platforms.platformtypes;
import java.awt.image.BufferedImage;
import java.util.List;
import powerups.PowerUp;

public class SimplePlatform extends AbstractPlatform {
	
	private static final long serialVersionUID = 7514750773895804951L;
	private PowerUp myPowerUp;

	public SimplePlatform(BufferedImage[] im, double x, double y, List<String> images) {
	    super(im, x, y, images);
	}

	@Override
	protected void doBehavior(double speed, double distance) {
		return;	
	}
	
	protected void releaseItem() {
		if (myPowerUp != null) {
			myPowerUp.setActive(true);
			myPowerUp.setLocation(getX(), getY() + 20);
			myPowerUp.setHorizontalSpeed(0.02);	
		}
	}
	
	protected void setPowerUp(PowerUp powerup) {
		myPowerUp = powerup;
		myPowerUp.setActive(false);
	}
	
	@Override
	public String toString() {
		return "platform";
	}
}
