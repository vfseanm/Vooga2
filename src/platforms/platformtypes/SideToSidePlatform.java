package platforms.platformtypes;




public class SideToSidePlatform extends DecoratedPlatform {

	private static final long serialVersionUID = -1092406048949643816L;

	
	public SideToSidePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		mySpeed = Double.parseDouble(myPlatformResources.getString("SideToSideSpeed"));
		myDistance = Double.parseDouble(myPlatformResources.getString("SideToSideDistance"));
	}
	
	protected void doBehavior(double speed, double distance) {
		double time = (distance * myDistanceOffset) / speed;
		if (myTimer.getPassedFrames() % (time * 2) == 0) {
			setHorizontalSpeed(speed/mySpeedOffset);
		}
		else if (myTimer.getPassedFrames() % time == 0) {
			setHorizontalSpeed(-speed/mySpeedOffset);
		}
		myTimer.update();
	}
	
	
	
	public String toString() {
		return myPlatformResources.getString("SideToSide") + myDecoratorComponent.toString();
	}

	public Object clone() {
		AbstractPlatform toWrap = null;
		if (myDecoratorComponent != null) {
			toWrap = (AbstractPlatform) myDecoratorComponent.clone();
		}
		return new SideToSidePlatform(toWrap);
	}
}
