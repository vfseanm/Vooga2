package platforms.fsmframework;

/**
 * Defines an event for a platform state machine that determines whether a state
 * machine changes state based upon the activation or deactivation of an in-game
 * switch
 * 
 * @author Nick Gordon
 * 
 */
public class SwitchEvent extends DecoratedEvent {

	private boolean myOnOff = false;
	private PlatformSwitch mySwitch;

	/**
	 * Serves as a constructor to create a SwitchEvent
	 * 
	 * @param pSwitch
	 *            PlatformSwitch representing the in-game switch for this class
	 * @param event
	 *            event component that this class decorates
	 */
	public SwitchEvent(PlatformSwitch pSwitch, AbstractEvent event) {
		super(event);
		mySwitch = pSwitch;
	}

	/**
	 * Returns true for the state machine to advance to its next state if
	 * mySwitch has been activated
	 * 
	 * @return boolean representing whether or not the state machine should
	 *         advance to the next state.
	 */
	@Override
	public boolean isNextState() {
		if (myOnOff == false && mySwitch.getSwitchOnOff()) {
			myOnOff = mySwitch.getSwitchOnOff();
			return true;
		}
		return false;
	}

	/**
	 * Returns true for the state machine to revert to its previous state if
	 * mySwitch has been deactivated
	 * 
	 * @return boolean representing whether or not the state machine should
	 *         revert to the previous state.
	 */
	@Override
	public boolean isPreviousState() {
		if (myOnOff == true && !mySwitch.getSwitchOnOff()) {
			myOnOff = mySwitch.getSwitchOnOff();
			return true;
		}
		return false;
	}
}