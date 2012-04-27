package platforms.platformtypes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import collisions.CollisionAction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.ReflectionUtil;
import editor.json.SpriteJsonData;

import java.util.ResourceBundle;
import sprite.AnimatedGameSprite;

/**
 * This class provides abstract functionality for platform classes. It is the
 * super class for every type of platform.
 * 
 * @author Nick Gordon
 * 
 */
public abstract class AbstractPlatform extends AnimatedGameSprite {

	private static final long serialVersionUID = 1483938382856783084L;
	transient protected ResourceBundle myPlatformResources = ResourceBundle
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
		setGroup(myPlatformResources.getString("PlatformGroup"));
	
	}

	/**
	 * Super constructor used for decorated platforms
	 */
	protected AbstractPlatform() {
		myPlatformResources = ResourceBundle
				.getBundle("platforms.PlatformResourceBundle");
		setGroup(myPlatformResources.getString("PlatformGroup"));
	}

	public String getGroup() {
		return myPlatformResources.getString("PlatformGroup");
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

	public Class<? extends CollisionAction> getActionClass() {
		return PlatformAction.class;
	}

	@SuppressWarnings({ "rawtypes" })
	public String toJson() {
		Gson gson = new Gson();
		List<String> classNames = new ArrayList<String>();
		if (!this.getClass().equals(SimplePlatform.class)) {
			for (Class c : ((DecoratedPlatform) this).getClassesOfDecorators()) {
				classNames.add(c.toString());
			}
		}
		String additionalInformation = gson.toJson(classNames);
		return gson.toJson(new SpriteJsonData(this, additionalInformation));

	}

	public AbstractPlatform fromJson(String json) {
		Gson gson = new Gson();
		Type collectionType = new TypeToken<List<String>>() {
		}.getType();
		SpriteJsonData spriteData = gson.fromJson(json, SpriteJsonData.class);
		AbstractPlatform platform = new SimplePlatform(spriteData.getX(),
				spriteData.getY(), spriteData.getImageNames());
		platform.setGroup(spriteData.getGroup());
		List<String> classList = gson.fromJson(
				spriteData.getAdditionalInformation(), collectionType);
		return (AbstractPlatform) ReflectionUtil
				.wrapObject(classList, platform);
	}
}
