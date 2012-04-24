package platforms.fsmframework;

import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.UpDownPlatform;

public class OnState extends PlatformState {

	private AbstractPlatform myDecorator;
	
	public OnState(AbstractPlatform platform) {
		myDecorator = new UpDownPlatform(platform);
	}
	
	@Override
 	public void handle(long elapsedTime) {
		myDecorator.update(elapsedTime);	
	}
	
	//public getNextState();

}
