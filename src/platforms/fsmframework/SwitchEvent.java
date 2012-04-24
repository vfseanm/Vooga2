package platforms.fsmframework;

import java.util.List;

import platforms.platformtypes.AbstractPlatform;

public class SwitchEvent extends AbstractEvent {
	
	private boolean myOnOff;
	private PlatformSwitch mySwitch;

	public SwitchEvent(PlatformSwitch pSwitch, List<AbstractPlatformState> myTransitionList, List<AbstractPlatform> platforms) {
		super(myTransitionList, platforms);
		mySwitch = pSwitch;
		pSwitch.getSwitchOnOff();
	}

	@Override
	public boolean moveToNextState() {
		if (myOnOff == false && mySwitch.getSwitchOnOff()) {
			myOnOff = mySwitch.getSwitchOnOff();
			return true;
		}
		return false;
	}

	@Override
	public boolean moveToPreviousState() {
		if (myOnOff == true && !mySwitch.getSwitchOnOff()) {
			myOnOff = mySwitch.getSwitchOnOff();
			return true;
		}
		return false;
	}

}
