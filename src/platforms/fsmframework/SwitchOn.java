package platforms.fsmframework;

import java.util.List;

import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.UpDownPlatform;

public class SwitchOn extends AbstractPlatformState {

	
	public SwitchOn(List<AbstractPlatform> platforms) {
		super(platforms);
		
	}

	public void thing() {
		for (AbstractPlatform platform : myControlledPlatforms) {
			platform = new UpDownPlatform(platform);
		}
	}

	@Override
	public void handle(long elapsedTime) {
		for (AbstractPlatform platform : myControlledPlatforms) {
			platform.update(elapsedTime);	
		}
	}
}


