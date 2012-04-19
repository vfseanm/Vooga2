package platforms.platformtypes;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import enemies.Enemy;

import attributes.Attribute;
import bonusobjects.BonusObject;

public class SimplePlatform extends AbstractPlatform {
	
	private static final long serialVersionUID = 7514750773895804951L;
	private BonusObject myBonusObject;

	public SimplePlatform(double x, double y, List<String> imageNames) {
	    super(x, y, imageNames);
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

	 public Object clone() {
	        List<String> imageNames = new ArrayList<String>();
	        imageNames.addAll(this.getImageNames());
	        SimplePlatform e = new SimplePlatform(this.getX(), this.getY(),imageNames);
	        return e;
	}



}
