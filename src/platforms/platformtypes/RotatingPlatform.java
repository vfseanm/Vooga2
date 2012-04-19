package platforms.platformtypes;

public class RotatingPlatform extends DecoratedPlatform {
	
	private static final long serialVersionUID = 7537483544825845415L;
	private int myFrames = 0;
	private int myWidth;
	private int myHeight;
	private int numFrames = Integer.parseInt(myPlatformResources.getString("RotateFrames")); //number of rotations for 1 complete cycle
	
	
	public RotatingPlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		setLoopAnim(true);
		myHeight = getImages()[0].getHeight();
		myWidth = getImages()[0].getWidth();
	}
	
	public void doBehavior(double speed, double distance) {
		rotateLeftAxisClockwise(myDelay);	
	}
	

	public void rotateCenterAxis(int delay) {
		myTimer.update();
		System.out.println(myTimer.getPassedFrames() % delay == 0);
		if (myTimer.getPassedFrames() % delay == 0) {
			myFrames++;
			if (myFrames % (numFrames/2) == 0) {
				setLocation(getX() - myWidth / 2.0 + myHeight / 2.0, getY() + myWidth / 2.0 - myHeight / 2.0);
				setFrame(0);
			}
			else if (myFrames % (numFrames/2) == 1) {
				setLocation(getX() + myWidth / 2.0 - myHeight / 2.0, getY() - myWidth/ 2.0 + myHeight / 2.0);
				setFrame(1);
			}
		}
	}

	public void rotateLeftAxisClockwise(int delay) {
		myTimer.update();

		if (myTimer.getPassedFrames() % delay == 0) {
			myFrames++;
			if (myFrames % numFrames == 0) {
				setLocation(getX(), getY() + myWidth - myHeight);
				setFrame(0);
			}
			else if (myFrames % numFrames == 1) {
				setFrame(1);
			}
			else if (myFrames % numFrames == 2) {
				setFrame(0);
				setLocation(getX() - myWidth + myHeight, getY());
			}
			else if (myFrames % numFrames == 3) {
				setFrame(1);
				setLocation(getX() + myWidth - myHeight, getY() - myWidth + myHeight);
			}
		}
	}

	public String toString() {
		return myPlatformResources.getString("Rotating") + myDecoratorComponent.toString();
	}

	public Object clone() {
		AbstractPlatform toWrap = null;
		if (myDecoratorComponent != null) {
			toWrap = (AbstractPlatform) myDecoratorComponent.clone();
		}
		return new RotatingPlatform(toWrap);

	}

}
