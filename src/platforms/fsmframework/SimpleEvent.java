package platforms.fsmframework;

import java.util.List;
import platforms.platformtypes.AbstractPlatform;

/**
 * Defines a simple event that serves as the basis for combining DecoratedEvents
 * using the decorator pattern. Does not allow a state machine to change state
 * on its own. Also holds data for the state machine such as the platforms the
 * machine controls, the state progression for the machine, and the state
 * machine's current state.
 * 
 * @author Nick Gordon
 * 
 */
public class SimpleEvent extends AbstractEvent {

	protected List<AbstractPlatform> myPlatforms;
	protected List<AbstractPlatformState> myTransitionList;
	protected AbstractPlatformState myCurrentState;
	protected int myCurrentStateIndex;

	/**
	 * Constructor for a simple event. Sets the progression of states for the
	 * machine and the platforms that the machine controls.
	 * 
	 * @param transitionList
	 *            list representing the progression of states for a platform
	 *            state machine
	 * @param platforms
	 *            list representing the platforms that a platform state machine
	 *            controls.
	 */

	public SimpleEvent(List<AbstractPlatformState> transitionList,
			List<AbstractPlatform> platforms) {
		myTransitionList = transitionList;
		setControlledPlatforms(platforms);
		setToInitialState();
	}

	/**
	 * Sets the list of platforms that a platform state machine is associated
	 * with
	 * 
	 * @param List
	 *            <AbstractPlatform> list of platforms that a platform state
	 *            machine will control
	 */
	public void setControlledPlatforms(List<AbstractPlatform> platforms) {
		myPlatforms = platforms;
		for (AbstractPlatformState state : myTransitionList) {
			state.setControlledPlatforms(platforms);
		}
	}

	/**
	 * Sets the state of a platform state machine
	 * 
	 * @param AbstractPlatformState
	 *            possible state of the machine
	 */
	public void setState(AbstractPlatformState state) {
		if (myTransitionList.contains(state)) {
			myCurrentState = state;
			myCurrentState.decoratePlatforms();
			int index = myTransitionList.indexOf(state);
			myCurrentStateIndex = index;
		}
	}

	/**
	 * Sets the state of a platform state machine to its initial state
	 */
	public void setToInitialState() {
		if (myTransitionList != null) {
			myCurrentState = myTransitionList.get(0);
			myCurrentState.decoratePlatforms();
			myCurrentStateIndex = 0;
		}
	}

	/**
	 * Retrieves the state of a platform state machine
	 * 
	 * @return AbstractPlatformState current state of a platform state machine
	 */
	public AbstractPlatformState getState() {
		return myCurrentState;
	}

	/**
	 * Advances the state of a state machine to the next state in its state
	 * progression
	 */
	public void changeToNextState() {
		if (myTransitionList == null) {
			return;
		}
		myCurrentStateIndex++;
		myCurrentState = myTransitionList.get(myCurrentStateIndex
				% myTransitionList.size());
		myCurrentState.decoratePlatforms();
	}

	/**
	 * Reverts the state of a state machine to the previous state in its state
	 * progression
	 */
	public void changeToPreviousState() {
		if (myTransitionList == null) {
			return;
		}
		myCurrentStateIndex--;
		myCurrentState = myTransitionList.get(myCurrentStateIndex
				% myTransitionList.size());
		myCurrentState.decoratePlatforms();
	}

	/**
	 * Updates the current state of a platform machine
	 */
	public void update(long elapsedTime) {
		myCurrentState.handle(elapsedTime);
	}
}
