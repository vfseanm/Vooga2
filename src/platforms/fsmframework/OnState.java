package platforms.fsmframework;

import platforms.platformtypes.SimplePlatform;
import platforms.platformtypes.UpDownPlatform;

public class OnState extends PlatformState {

	
	private UpDownPlatform myDecorator;
	
	public OnState(SimplePlatform platform) {
		myDecorator = new UpDownPlatform(platform);
	}
	
	@Override
 	public void handle(long elapsedTime) {
		myDecorator.update(elapsedTime);	
	}

}
