package platforms.fsmframework;

import java.util.List;
import platforms.platformtypes.AbstractPlatform;

public abstract class AbstractPlatformState {

	protected List<AbstractPlatform>  myControlledPlatforms;
	
	public AbstractPlatformState(List<AbstractPlatform> platforms) {
		myControlledPlatforms = platforms;
	}
/*	public void setControlledPlatforms(List<AbstractPlatform> platforms) {
		myControlledPlatforms = platforms;
	}*/
	
	public abstract void handle(long elapsedTime);
	public abstract void thing();
}
