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
	transient protected ResourceBundle myPlatformResources = ResourceBundle
    .getBundle("platforms.PlatformResourceBundle");;
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
		/*myPlatformResources = ResourceBundle
        .getBundle("platforms.PlatformResourceBundle");*/
	}

	/**
	 * Super constructor used for decorated platforms
	 */
	protected AbstractPlatform() {
	    myPlatformResources = ResourceBundle
        .getBundle("platforms.PlatformResourceBundle");
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
	
	public abstract Object clone();
	
}
