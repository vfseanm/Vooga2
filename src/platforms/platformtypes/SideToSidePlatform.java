package platforms.platformtypes;

import editor.json.JsonableSprite;
import editor.json.SpriteFactory;

/**
 * Class used to decorate platforms in order to add side to side functionality
 * i.e. this class will allow the user to add functionality to a platform to
 * allow it to traverse a horizontal distance repeatedly.
 * 
 * @author Nick Gordon
 */
public class SideToSidePlatform extends DecoratedPlatform implements
		JsonableSprite {

	private static final long serialVersionUID = -1092406048949643816L;

	/**
	 * Constructor for a side to side platform
	 * 
	 * @param decoratorComponent
	 *            AbstractPlatform representing the platform that this class
	 *            decorates
	 */
	public SideToSidePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		mySpeed = Double.parseDouble(myPlatformResources
				.getString("SideToSideSpeed"));
		myDistance = Double.parseDouble(myPlatformResources
				.getString("SideToSideDistance"));
	}

	/**
	 * Function to implement the behavior of this object and which is called in
	 * the update function defined in its super class. In this case, moving the
	 * platform horizontally back and forth across a specific distance
	 * 
	 * @param speed
	 *            double defines the movement speed of the platform
	 * @param distance
	 *            double defines the movement range of the platform
	 */
	@Override
	protected void doBehavior(double speed, double distance) {
		double time = (distance * myDistanceOffset) / speed;
		if (myTimer.getPassedFrames() % (time * 2) == 0) {
			setHorizontalSpeed(speed / mySpeedOffset);
		} else if (myTimer.getPassedFrames() % time == 0) {
			setHorizontalSpeed(-speed / mySpeedOffset);
		}
		myTimer.update();
	}

	/**
	 * Used in saving platforms in the level editor this method creates a string
	 * representing this platform class as well as the platforms that it may
	 * decorate.
	 */
	@Override
	public String toString() {
		return myPlatformResources.getString("SideToSide")
				+ myDecoratorComponent.toString();
	}

	public Object clone() {
		AbstractPlatform toWrap = null;
		if (myDecoratorComponent != null) {
			toWrap = (AbstractPlatform) myDecoratorComponent.clone();

		}
		return new SideToSidePlatform(toWrap);

	}

	private SideToSidePlatform() {
	};

	public static SpriteFactory<SideToSidePlatform> getFactory() {
		return new SpriteFactory<SideToSidePlatform>(new SideToSidePlatform());
	}

}
