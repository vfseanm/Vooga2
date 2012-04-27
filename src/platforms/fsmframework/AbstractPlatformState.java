package platforms.fsmframework;

import java.util.ArrayList;
import java.util.List;
import platforms.platformtypes.AbstractPlatform;

public abstract class AbstractPlatformState {

	protected List<AbstractPlatform>  myControlledPlatforms;
	protected List<AbstractPlatform> newPlatforms = new ArrayList<AbstractPlatform>();
	
	public AbstractPlatformState() {}
	
	public void setControlledPlatforms(List<AbstractPlatform> platforms) {
		myControlledPlatforms = platforms;
	}
	
	public abstract void handle(long elapsedTime);
	public abstract void decoratePlatforms();
}