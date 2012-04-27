package platforms.platformtypes;

import editor.json.JsonableSprite;
import editor.json.SpriteFactory;

/**
 * Creates a platform decorator to add functionality to a platform to allow the
 * platform to rotate around various axis, such as its center axis or left axis,
 * etc...
 * 
 * @author Nick Gordon
 * 
 */
public class RotatingPlatform extends DecoratedPlatform implements
		JsonableSprite {

	private static final long serialVersionUID = 7537483544825845415L;
	private int myFrames = 0;
	private int myWidth;
	private int myHeight;
	private int numFrames = Integer.parseInt(myPlatformResources
			.getString("RotateFrames"));; // number of rotations for 1 complete
											// cycle

	/**
	 * Constructor to create a rotating platform
	 * 
	 * @param decoratorComponent
	 *            AbstractPlatform representing the platform this class is
	 *            decorating
	 */
	public RotatingPlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		setLoopAnim(true);
		myHeight = getImages()[0].getHeight();
		myWidth = getImages()[0].getWidth();
	}

	/**
	 * Function to implement the behavior of this object and which is called in
	 * the update function defined in its super class. In this case, rotating
	 * around the platform's left axis
	 * 
	 * @param speed
	 *            double that is unused in this class' implementation of this
	 *            function
	 * @param distance
	 *            double that is unused in this class' implementation of this
	 *            function
	 */
	@Override
	public void doBehavior(double speed, double distance) {
		rotateLeftAxisClockwise(myDelay);
	}

	/**
	 * Rotates the platform clockwise around the center axis at a speed
	 * determined by the delay parameter
	 * 
	 * @param delay
	 *            int that determines the rotational speed. The higher the delay
	 *            the slower the platform will rotate
	 */
	public void rotateCenterAxis(int delay) {
		myTimer.update();
		System.out.println(myTimer.getPassedFrames() % delay == 0);
		if (myTimer.getPassedFrames() % delay == 0) {
			myFrames++;
			if (myFrames % (numFrames / 2) == 0) {
				setLocation(getX() - myWidth / 2.0 + myHeight / 2.0, getY()
						+ myWidth / 2.0 - myHeight / 2.0);
				setFrame(0);
			} else if (myFrames % (numFrames / 2) == 1) {
				setLocation(getX() + myWidth / 2.0 - myHeight / 2.0, getY()
						- myWidth / 2.0 + myHeight / 2.0);
				setFrame(1);
			}
		}
	}

	/**
	 * Rotates the platform clockwise around the left axis at a speed determined
	 * by the delay parameter
	 * 
	 * @param delay
	 *            int that determines the rotational speed. The higher the delay
	 *            the slower the platform will rotate
	 */

	public void rotateLeftAxisClockwise(int delay) {
		myTimer.update();

		if (myTimer.getPassedFrames() % delay == 0) {
			myFrames++;
			if (myFrames % numFrames == 0) {
				setLocation(getX(), getY() + myWidth - myHeight);
				setFrame(0);
			} else if (myFrames % numFrames == 1) {
				setFrame(1);
			} else if (myFrames % numFrames == 2) {
				setFrame(0);
				setLocation(getX() - myWidth + myHeight, getY());
			} else if (myFrames % numFrames == 3) {
				setFrame(1);
				setLocation(getX() + myWidth - myHeight, getY() - myWidth
						+ myHeight);
			}
		}
	}

	/**
	 * Used in saving platforms in the level editor this method creates a string
	 * representing this platform class as well as the platforms that it may
	 * decorate.
	 */
	@Override
	public String toString() {
		return myPlatformResources.getString("Rotating")
				+ myDecoratorComponent.toString();
	}

	@Override
	public Object clone() {
		AbstractPlatform toWrap = null;
		toWrap = (AbstractPlatform) myDecoratorComponent.clone();
		return new RotatingPlatform(toWrap);
	}

	private RotatingPlatform() {
	};

	public static SpriteFactory<RotatingPlatform> getFactory() {
		return new SpriteFactory<RotatingPlatform>(new RotatingPlatform());
	}

}
