package platforms.fsmframework;
import java.util.List;
import platforms.platformtypes.*;

public abstract class AbstractEvent {
	
	
	public abstract void setControlledPlatforms(List<AbstractPlatform> platforms);

	public abstract void setState(AbstractPlatformState state);
	
	public abstract void setToInitialState();
	
	public abstract AbstractPlatformState getState();
	
	public abstract void changeToNextState();
	
	public abstract void changeToPreviousState();
	
	public abstract void update(long elapsedTime);
}
