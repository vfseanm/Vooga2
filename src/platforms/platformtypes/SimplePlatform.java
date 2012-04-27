package platforms.platformtypes;

import java.util.ArrayList;
import java.util.List;
import editor.json.JsonableSprite;
import editor.json.SpriteFactory;

/**
 * Class that defines a simple platform. This class serves as the basis for
 * decorating platforms and combining their functionality. Unlike decorating
 * platforms this class contains actual data, i.e. x and y positions and images.
 * Decorating platforms are used to add functionality and behaviors to this
 * class.
 * 
 * @author Nick Gordon
 * 
 */
public class SimplePlatform extends AbstractPlatform implements JsonableSprite {

	private static final long serialVersionUID = 7514750773895804951L;

	/**
	 * Constructor for a simple platform
	 * 
	 * @param x
	 *            double representing the x position of the platform
	 * @param y
	 *            double representing the y position of the platform
	 * @param imageNames
	 *            list of strings representing filenames of images associated
	 *            with the platform
	 */
	public SimplePlatform(double x, double y, List<String> imageNames) {
		super(x, y, imageNames);

	}

	/**
	 * Function implemented based upon framework in AbstractPlatform. Simple
	 * platforms have no special behavior therefore this function does nothing.
	 * 
	 * @param speed
	 *            double unused in this class
	 * @param distance
	 *            double unused in this class
	 */
	@Override
	protected void doBehavior(double speed, double distance) {
		return;
	}

	/**
	 * Used in saving platforms in the level editor this method creates a string
	 * representing this platform class as well as the platforms that it may
	 * decorate.
	 */
	@Override
	public String toString() {
		return  myPlatformResources.getString("Simple");
	}

	@Override
	public Object clone() {
		List<String> imageNames = new ArrayList<String>();
		imageNames.addAll(this.getImageNames());
		SimplePlatform e = new SimplePlatform(this.getX(), this.getY(),
				imageNames);
		e.setGroup(this.getGroup());
		return e;
	}

	private SimplePlatform() {
	};

	public static SpriteFactory<SimplePlatform> getFactory() {
		return new SpriteFactory<SimplePlatform>(new SimplePlatform());
	}

}
