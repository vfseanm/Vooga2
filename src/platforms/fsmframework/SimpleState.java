package platforms.fsmframework;
import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.SimplePlatform;


public class SimpleState extends AbstractPlatformState {

	public SimpleState() {}
	
	
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
