package platforms.fsmframework;

import java.util.ArrayList;
import java.util.List;
import platforms.platformtypes.AbstractPlatform;

/**
 * Defines an abstract class for creating a class representing the state of a
 * platform state machine
 * 
 * @author Nick Gordon
 * 
 */
public abstract class AbstractPlatformState {

	protected List<AbstractPlatform> myControlledPlatforms;
	protected List<AbstractPlatform> newPlatforms = new ArrayList<AbstractPlatform>();

	/**
	 * Serves as a general constructor for AbstractPlatformStates
	 */
	public AbstractPlatformState() {
	}

	/**
	 * Sets the list of platforms controlled by a platform state machine
	 * 
	 * @param platforms
	 *            list of platforms to be controlled by a platform state machine
	 */
	public void setControlledPlatforms(List<AbstractPlatform> platforms) {
		myControlledPlatforms = platforms;
	}

	/**
	 * Abstract method to be implemented in subclasses. Serves to update
	 * platforms whose behavior gets changed in subclass states
	 * 
	 * @param elapsedTime
	 *            amount of time that has elapsed in the game
	 */
	public abstract void handle(long elapsedTime);

	/**
	 * Abstract method to be implemented in subclasses. Serves to decorate
	 * platforms in a platform state machine so that they appear to change
	 * behavior in accordance with a specific state that the state machine is
	 * in.
	 */
	public abstract void decoratePlatforms();
}