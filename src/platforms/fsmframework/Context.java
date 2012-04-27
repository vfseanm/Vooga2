package platforms.fsmframework;

import java.util.List;

import platforms.platformtypes.AbstractPlatform;

public class Context {

	AbstractEvent myEvent;
	
	public Context(AbstractEvent event, List<AbstractPlatform> platforms) {
		myEvent = event;
		event.setControlledPlatforms(platforms);
	}
	
	public void update(long elapsedTime) {
		myEvent.update(elapsedTime);
	}
}

