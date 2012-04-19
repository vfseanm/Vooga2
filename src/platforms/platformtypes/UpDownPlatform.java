package platforms.platformtypes;


public class UpDownPlatform extends DecoratedPlatform {

	private static final long serialVersionUID = -3578102991430723896L;
	


	public UpDownPlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		mySpeed = Double.parseDouble(myPlatformResources.getString("SideToSideSpeed"));
		myDistance = Double.parseDouble(myPlatformResources.getString("SideToSideDistance"));
	}
	
	public void doBehavior(double speed, double distance) {
		double time = (distance * myDistanceOffset) / speed;
		if (myTimer.getPassedFrames() % (time * 2) == 0) {
			setVerticalSpeed(speed / mySpeedOffset);
		}
		else if (myTimer.getPassedFrames() % time == 0) {
			setVerticalSpeed(-speed/ mySpeedOffset);
		}
		myTimer.update();
	}
	
	public String toString() {
		return myPlatformResources.getString("UpDown")+ myDecoratorComponent.toString();
	}
	
	public Object clone() {
		AbstractPlatform toWrap = null;
		if (myDecoratorComponent != null) {
			toWrap = (AbstractPlatform) myDecoratorComponent.clone();
		}
		return new UpDownPlatform(toWrap);
	}
}
