package platforms.fsmframework;

import java.util.List;
import java.util.ResourceBundle;

import platforms.platformtypes.*;

/**
 * Abstract class for defining events that define when and how a platform state
 * machine should change state
 * 
 * @author Nick Gordon
 * 
 */
public abstract class AbstractEvent {

	transient protected ResourceBundle myPlatformResources = ResourceBundle
	.getBundle("platforms.PlatformResourceBundle");
	
	/**
	 * Sets the platforms controlled by the state machine associated with the
	 * event being called.
	 * 
	 * @param platforms
	 *            list of platforms controlled by the state machine
	 */
	public abstract void setControlledPlatforms(List<AbstractPlatform> platforms);

	/**
	 * sets the state of the state machine to the state passed as a parameter
	 * 
	 * @param state
	 *            possible state machine state
	 */

	public abstract void setState(AbstractPlatformState state);

	/**
	 * set the state of the state machine to its initial state
	 */
	public abstract void setToInitialState();

	/**
	 * Retrieves the current state of the machine
	 * 
	 * @return AbstractPlatformState current state of the state machine
	 */

	public abstract AbstractPlatformState getState();

	/**
	 * Advances the state of the state machine to the next state of the
	 * machine's state progression
	 */
	public abstract void changeToNextState();

	/**
	 * Reverts the state of the state machine to the previous state of the
	 * machine's state progression
	 */
	public abstract void changeToPreviousState();

	/**
	 * Updates the state machine
	 * 
	 * @param elapsedTime
	 *            time passed in the game
	 */
	public abstract void update(long elapsedTime);
}
