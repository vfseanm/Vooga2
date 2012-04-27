package platforms.fsmframework;

import java.util.ArrayList;

import java.util.List;

import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.UpDownPlatform;

/**
 * Creates a state for a platform state machine that allows all platforms
 * associated with it to act as UpDownPlatforms
 * 
 * @author Nick Gordon
 * 
 */
public class UpDownState extends AbstractPlatformState {

	private List<AbstractPlatform> newPlatforms = new ArrayList<AbstractPlatform>();

	/**
	 * Serves as a constructor to instantiate an UpDownState object
	 */
	public UpDownState() {
	}

	/**
	 * Decorates all platforms associated with a platform state machine with an
	 * UpDownPlatform
	 */
    @Override
	public void decoratePlatforms() {
		newPlatforms = new ArrayList<AbstractPlatform>();
		for (AbstractPlatform platform : myControlledPlatforms) {
			newPlatforms.add(new UpDownPlatform(platform));
		}
	}

	/**
	 * Updates the behaviors of all platforms associated with a platform state
	 * machine according to the behavior established by this state
	 */
	@Override
	public void handle(long elapsedTime) {
		for (AbstractPlatform platform : newPlatforms) {
			platform.update(elapsedTime);
		}
	}
}
