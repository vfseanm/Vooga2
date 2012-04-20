package platforms.fsmframework;
import java.awt.Graphics2D;
import java.util.List;
import platforms.FrameTimer;
import platforms.platformtypes.AbstractPlatform;
import sprite.AnimatedGameSprite;


public class PlatformSwitch extends AnimatedGameSprite {
	
	private static final long serialVersionUID = 1L;
	private AbstractPlatform myPlatform;
	private PlatformState myState;
	private FrameTimer myTimer = new FrameTimer();
	private boolean SwitchOn = false;

	public PlatformSwitch(AbstractPlatform controlledPlatform, double x, double y, List<String> imSources) {
		super(x, y, imSources);
		myPlatform = controlledPlatform;
		myState = new OffState(myPlatform);
	}

	public void pushed() {
		setFrame(1);
		myTimer.clear();
		SwitchOn = true;
		myState = new OnState(myPlatform);
	}
	
	public void update(long elapsedTime) {
		if (myTimer.getPassedFrames() >= 1000 && SwitchOn) {
			setFrame(0);
			SwitchOn = false;
			myState = new OffState(myPlatform);
		}
		myTimer.update();
		myState.handle(elapsedTime);
		super.update(elapsedTime);
	}
	
	public void render(Graphics2D graphics) {
		myPlatform.render(graphics);
		super.render(graphics);
	}
}

