package platforms.platformtypes;
import java.awt.image.BufferedImage;
import java.util.List;

import bonusobjects.BonusObject;

public class SimplePlatform extends AbstractPlatform {
	
	private static final long serialVersionUID = 7514750773895804951L;
	private BonusObject myBonusObject;

	public SimplePlatform(BufferedImage[] im, double x, double y, List<String> images) {
	    super(im, x, y, images);
	}

	@Override
	protected void doBehavior(double speed, double distance) {
		return;	
	}
	
	protected void releaseItem() {
		if (myBonusObject != null) {
			myBonusObject.setActive(true);
			myBonusObject.setLocation(getX(), getY() + 20);
			myBonusObject.setHorizontalSpeed(0.02);	
		}
	}
	
	protected void setBonusObject(BonusObject bonusObject) {
		myBonusObject = bonusObject;
		myBonusObject.setActive(false);
	}
	
	@Override
	public String toString() {
		return "platform";
	}
	
	public void doBreak() {
		return;
	}




}
