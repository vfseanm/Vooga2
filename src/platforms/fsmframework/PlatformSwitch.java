package platforms.fsmframework;
import java.util.List;



import platforms.FrameTimer;
import platforms.platformtypes.PlatformAction;

import collisions.CollisionAction;
import sprite.AnimatedGameSprite;


public class PlatformSwitch extends AnimatedGameSprite {
	
	private static final long serialVersionUID = 1L;
	private boolean SwitchOn = false;
	private int myButtonDelay = 2000;
	private FrameTimer myTimer = new FrameTimer();

	public PlatformSwitch(double x, double y, List<String> imSources) {
		super(x, y, imSources);
		setGroup("PLATFORMSWITCH");
	}
	
	
	public void setOn(boolean bool) {
		SwitchOn = bool;
		if (bool) {
			setFrame(1);
		}
		else {
			setFrame(0);
		}	
	}
	
	public boolean getSwitchOnOff() {
		return SwitchOn;
	}
	
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (myTimer.getPassedFrames() >= myButtonDelay && SwitchOn) {
			setOn(false);
			myTimer.clear();	
		}
		myTimer.update();
	}
	
    public Class<? extends CollisionAction> getActionClass (){
    	return PlatformAction.class; 
    }
	
	public String getGroup(){
		return ("PLATFORMSWITCH");
	}
}

