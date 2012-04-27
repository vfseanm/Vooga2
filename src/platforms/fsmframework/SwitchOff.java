package platforms.fsmframework;
import java.util.ArrayList;

import java.util.List;
import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.SimplePlatform;


public class SwitchOff extends AbstractPlatformState {

	public SwitchOff() {}
	
	
	//from constructor
	public void decoratePlatforms() {
		for (AbstractPlatform platform : myControlledPlatforms) {
			newPlatforms.add(new SimplePlatform(platform.getX(), platform.getY(), platform.getImageNames()));
			platform.setHorizontalSpeed(0);
			platform.setVerticalSpeed(0);
			platform.setFrame(0);
	
		}
	}
	
	@Override
 	public void handle(long elapsedTime) {
		for (AbstractPlatform platform : newPlatforms) {
			platform.update(elapsedTime);	
	}
	
}
}
