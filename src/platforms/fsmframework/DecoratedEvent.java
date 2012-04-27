package platforms.fsmframework;

import java.util.List;

import platforms.platformtypes.AbstractPlatform;

/**
 * Creates a framework for creating new events to be used in a platform state
 * machine to determine when and how the state machine should change state.
 * Moreover, defines a framework for using the decorator pattern to combine
 * multiple events and allow a platforms state machine to be dependent on
 * multiple in-game occurrences.
 * 
 * @author Nick Gordon
 * 
 */
public abstract class DecoratedEvent extends AbstractEvent {

	AbstractEvent myDecoratorComponent;

	/**
	 * Constructor for creating a new DecoratedEvent
	 * 
	 * @param event
	 *            event that this class is decorating
	 */
	public DecoratedEvent(AbstractEvent event) {
		myDecoratorComponent = event;
	}

	/**
	 * Abstract class which returns a boolean representing whether or not a
	 * state machine should advance to the next state in its state progression.
	 * 
	 * @return boolean
	 */
	public abstract boolean isNextState();

	/**
	 * Abstract class which returns a boolean representing whether or not a
	 * state machine should advance to the next state in its state progression.
	 * 
	 * @return boolean
	 */
	public abstract boolean isPreviousState();

	/**
	 * Sets the list of platforms that this event is associated with
	 */
	@Override
	public void setControlledPlatforms(List<AbstractPlatform> platforms) {
		myDecoratorComponent.setControlledPlatforms(platforms);

	}

	/**
	 * Sets the state of a platform state machine
	 */

	@Override
	public void setState(AbstractPlatformState state) {
		myDecoratorComponent.setState(state);

	}

	/**
	 * Sets the state of a platform state machine to the initial state of its
	 * state progression.
	 */

	@Override
	public void setToInitialState() {
		myDecoratorComponent.setToInitialState();

	}

	/**
	 * Gets the current state of a platform state machine
	 * 
	 * @return AbstractPlatformState representing the current state of a
	 *         platform state machine
	 */

	@Override
	public AbstractPlatformState getState() {
		return myDecoratorComponent.getState();
	}

	/**
	 * Delegates a call to its DecoratorComponent to advance the state of a
	 * platform state machine to its next state
	 */
	@Override
	public void changeToNextState() {
		myDecoratorComponent.changeToNextState();
	}

	/**
	 * Delegates a call to its DecoratorComponent to revert the state of a
	 * platform state machine to its previous state
	 */
	@Override
	public void changeToPreviousState() {
		myDecoratorComponent.changeToPreviousState();

	}

	/**
	 * Updates the event and checks to see if the state machine should change
	 * state.
	 */
	@Override
	public void update(long elapsedTime) {
		if (isNextState()) {
			changeToNextState();
		}
		if (isPreviousState()) {
			changeToPreviousState();
		}
		myDecoratorComponent.update(elapsedTime);
	}

}
