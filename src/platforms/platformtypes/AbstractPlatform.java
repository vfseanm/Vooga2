package platforms.platformtypes;

import java.util.List;
import java.util.ResourceBundle;
import sprite.AnimatedGameSprite;

/**
 * This class provides abstract functionality for platform classes. It is the
 * super class for every type of platform.
 * 
 * @author yankeenjg
 * 
 */
public abstract class AbstractPlatform extends AnimatedGameSprite {

	private static final long serialVersionUID = 1483938382856783084L;
	protected ResourceBundle myPlatformResources = ResourceBundle
			.getBundle("platforms.PlatformResourceBundle");

	/**
	 * Super constructor used for a simple platform
	 * 
	 * @param x
	 *            double that represents the x position of the platform in the
	 *            game
	 * @param y
	 *            double represents the y position of the platform in the game
	 * @param imageSources
	 *            list of filenames used to retrieve image files that will
	 *            represent the platform in the game
	 */

	protected AbstractPlatform(double x, double y, List<String> imageSources) {
		super(x, y, imageSources);
	}

	/**
	 * Super constructor used for decorated platforms
	 */
	protected AbstractPlatform() {
	}

	/**
	 * Function that implements the behavior of each type of platform
	 * 
	 * @param speed
	 *            double that defines the movement speed of moving platforms
	 * @param distance
	 *            double that defines the distance that a moving platform should
	 *            move back and forth across
	 */
	protected abstract void doBehavior(double speed, double distance);

	/**
	 * Used in saving platforms in the level editor this method creates a string
	 * representing this platform class as well as the platforms that it may
	 * decorate.
	 */
	@Override
	public abstract String toString();

	public abstract Object clone();
}
