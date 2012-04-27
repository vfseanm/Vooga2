package platforms.fsmframework;

public class SwitchEvent extends DecoratedEvent {
	
	private boolean myOnOff = false;
	private PlatformSwitch mySwitch;


	public SwitchEvent(PlatformSwitch pSwitch, AbstractEvent event) {
		super(event);
		mySwitch = pSwitch;
		//myOnOff = pSwitch.getSwitchOnOff();
	}

	@Override
	public boolean isNextState() {
		if (myOnOff == false && mySwitch.getSwitchOnOff()) {
			myOnOff = mySwitch.getSwitchOnOff();
			//System.out.println("nextState yay!");
			return true;
		}
		return false;
	}

	@Override
	public boolean isPreviousState() {
		if (myOnOff == true && !mySwitch.getSwitchOnOff()) {
			myOnOff = mySwitch.getSwitchOnOff();
			return true;
		}
		return false;
	}
}