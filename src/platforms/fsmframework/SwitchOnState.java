package platforms.fsmframework;

import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.*;

public class SwitchOnState extends PlatformState {
	
	AbstractPlatform myPlatform;
	
	public SwitchOnState(AbstractPlatform platform) {
		myPlatform = platform;
	}
	
	public AbstractPlatform handle() {
		myPlatform = new SimplePlatform(myPlatform.getX(), myPlatform.getY(), myPlatform.getImageNames());
		myPlatform = new UpDownPlatform(myPlatform);
		return myPlatform;
	}

}
