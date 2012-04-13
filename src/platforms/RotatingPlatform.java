package platforms;

import java.awt.image.BufferedImage;

public class RotatingPlatform extends DecoratedPlatform {
	
	private static final long serialVersionUID = 7537483544825845415L;
	int myFrames = 0;
	int myWidth;
	int myHeight;
	double myDelay = 100;
	
	public RotatingPlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		setLoopAnim(true);
		myHeight = getImages()[0].getHeight();
		myWidth = getImages()[0].getWidth();
	}
	
	protected void doBehavior(double speed, double distance) {
		if (!isAnimate()) {
			setAnimate(true);
		}
		System.out.println(getImages().length);
		System.out.println(getFrame());
		rotateCenterAxis(myDelay);
	}
	
	public void rotateCenterAxis(double delay) {
		myTimer.update();
		if (myTimer.getPassedFrames() % delay == 0) {
			updateAnimation();
			myFrames++;
			if (myFrames % 2 == 0) {
				setLocation(getX() - myWidth / 2.0 + myHeight / 2.0, getY());
			}
			else if (myFrames % 2 == 1) {
				setLocation(getX() + myWidth / 2.0 - myHeight / 2.0, getY() - myWidth/ 2.0 + myHeight / 2.0);
			}
		}
	}
	
	public String toString() {
		return "rotating" + myDecoratorComponent.toString();
	}

	
}
