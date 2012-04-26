package platforms.fsmframework;
import java.util.List;


import platforms.platformtypes.PlatformAction;

import collisions.CollisionAction;
import sprite.AnimatedGameSprite;


public class PlatformSwitch extends AnimatedGameSprite {
	
	private static final long serialVersionUID = 1L;
	private boolean SwitchOn = false;

	public PlatformSwitch(double x, double y, List<String> imSources) {
		super(x, y, imSources);
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
	
    public Class<? extends CollisionAction> getActionClass (){
    	return PlatformAction.class; 
    }
	
	public String getGroup(){
		return ("PLATFORMSWITCH");
	}
}

