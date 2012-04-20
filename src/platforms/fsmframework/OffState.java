package platforms.fsmframework;

import platforms.platformtypes.SimplePlatform;

public class OffState extends PlatformState {
	
	private SimplePlatform myDecorator;
	
	public OffState(SimplePlatform platform) {
		myDecorator = platform;
		platform.setVerticalSpeed(0);
		platform.setHorizontalSpeed(0);
		platform.setFrame(0);
	}
	
	@Override
 	public void handle(long elapsedTime) {
		myDecorator.update(elapsedTime);	
	}
	
}