package platforms.fsmframework;

import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.SimplePlatform;

public class SwitchOffState extends PlatformState {
	
	AbstractPlatform myPlatform;
	
	public SwitchOffState(AbstractPlatform platform) {
		myPlatform = platform;
	}
	
	public AbstractPlatform handle() {
		myPlatform = new SimplePlatform(myPlatform.getX(), myPlatform.getY(), myPlatform.getImageNames());
		return myPlatform;
	}
}
