package platforms.fsmframework;

import java.util.List;
import platforms.FrameTimer;
import platforms.platformtypes.AbstractPlatform;
import sprite.AnimatedGameSprite;

public class PlatformSwitch extends AnimatedGameSprite implements IContext {

	private static final long serialVersionUID = 1L;
	
	private FrameTimer myTimer = new FrameTimer();
	private int myButtonDelay = 500;
	private PlatformState myState;
	private boolean SwitchOn = false;
	private AbstractPlatform myPlatform;
	
	
	public PlatformSwitch(AbstractPlatform controlledPlatform, double x, double y, List<String> imSources) {
		super(x, y, imSources);
		myPlatform = controlledPlatform;
		myState = new SwitchOffState(myPlatform);
	}
	
	public void pushed() {
		setFrame(1);
		myTimer.clear();
		SwitchOn = true;
		myState = new SwitchOnState(myPlatform);
	}
	
	public void update(long elapsedTime) {
		if (myTimer.getPassedFrames() >= myButtonDelay && SwitchOn) {
			setFrame(0);
			SwitchOn = false;
			myState = new SwitchOffState(myPlatform);
		}
		myTimer.update();
		myPlatform = myState.handle();
	}
}
