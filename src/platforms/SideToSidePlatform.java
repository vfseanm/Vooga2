package platforms;




public class SideToSidePlatform extends DecoratedPlatform {

	private static final long serialVersionUID = -1092406048949643816L;

	public SideToSidePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
	}
	
	protected void doBehavior(double speed, double distance) {
		double time = (distance * 5) / speed;
		if (myTimer.getPassedFrames() % (time * 2) == 0) {
			setHorizontalSpeed(speed/25);
		}
		else if (myTimer.getPassedFrames() % time == 0) {
			setHorizontalSpeed(-speed/25);
		}
		myTimer.update();
	}
	
	public String toString() {
		return "side to side" + myDecoratorComponent.toString();
	}

	
	

}
