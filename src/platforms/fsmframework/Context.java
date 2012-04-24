package platforms.fsmframework;

import java.util.List;
import platforms.platformtypes.AbstractPlatform;

public class Context {
	
	List<AbstractEvent> myEvents;
	
	public Context(List<AbstractEvent> events, List<AbstractPlatform> platforms) {
		myEvents = events;
		/*for (AbstractEvent event: myEvents) {
			event.setControlledPlatforms(platforms);
		}*/
	}
	
	public void update(long elapsedTime) {
		for (AbstractEvent event: myEvents) {
			event.update(elapsedTime);
		}
	}
	
	
	

}
