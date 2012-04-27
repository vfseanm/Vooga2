package platforms.fsmframework;

import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.SimplePlatform;

/**
 * Creates a state for a platform state machine that put allow all platforms
 * associated with it to act as simple platforms
 * 
 * @author Nick Gordon
 * 
 */
public class SimpleState extends AbstractPlatformState {

	/**
	 * Constructor for a SimpleState
	 */
	public SimpleState() {
	}

	/**
	 * Decorates the platforms associated with a platform state machine so that
	 * they appear to change behavior
	 */
	public void decoratePlatforms() {
		for (AbstractPlatform platform : myControlledPlatforms) {
			newPlatforms.add(new SimplePlatform(platform.getX(), platform
					.getY(), platform.getImageNames()));
			platform.setHorizontalSpeed(0);
			platform.setVerticalSpeed(0);
			platform.setFrame(0);

		}
	}

	/**
	 * Updates the behaviors of all platforms associated with a platform state
	 * machine according to the behavior established by this state
	 * 
	 * @param elapsedTime
	 *            time that has passed in the game
	 */
	@Override
	public void handle(long elapsedTime) {
		for (AbstractPlatform platform : newPlatforms) {
			platform.update(elapsedTime);
		}

	}
}
