
package platforms;


public class SideToSidePlatform extends DecoratedPlatform {

	private static final long serialVersionUID = -1092406048949643816L;

	public SideToSidePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
	}
	
	protected void doBehavior(double speed, double distance) {
		double time = (distance * 5) / speed;
		if (myTimer.getPassedFrames() % (time * 2) == 0) {
			setAllHorizontalSpeed(speed/25);
			//moveAll(200, 200);
			System.out.println("moveto200");
			System.out.println(getX() + " " + getY());
		}
		else if (myTimer.getPassedFrames() % time == 0) {
			setAllHorizontalSpeed(-speed/25);
			//moveAll(100, 100);
			System.out.println("moveto100");
		}
		myTimer.update();
		//System.out.println("moving!!");
	}
	
	public String toString() {
		return "side to side" + myDecoratorComponent.toString();
	}

	
	

}
