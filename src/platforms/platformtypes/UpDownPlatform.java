package platforms.platformtypes;


public class UpDownPlatform extends DecoratedPlatform {

	private static final long serialVersionUID = -3578102991430723896L;

	public UpDownPlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
	}
	
	protected void doBehavior(double speed, double distance) {
		double time = (distance * 5) / speed;
		if (myTimer.getPassedFrames() % (time * 2) == 0) {
			setVerticalSpeed(speed / 25);
		}
		else if (myTimer.getPassedFrames() % time == 0) {
			setVerticalSpeed(-speed/25);
		}
		myTimer.update();
	}
	
	public String toString() {
		return "up and down" + myDecoratorComponent.toString();
	}

}
