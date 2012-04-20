package platforms.fsmframework;

import platforms.platformtypes.AbstractPlatform;

public class OffState extends PlatformState {
	
	private AbstractPlatform myDecorator;
	
	public OffState(AbstractPlatform platform) {
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