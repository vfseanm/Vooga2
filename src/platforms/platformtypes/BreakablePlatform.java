package platforms.platformtypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.golden.gamedev.object.collision.CollisionGroup;

import sprite.AnimatedGameSprite;

import collisions.CollisionContext;
import collisions.CollisionSpec;
import editor.json.JsonableSprite;
import editor.json.SpriteFactory;

import bonusobjects.BonusObject;

/**
 * This class provides functionality that can be added on to simple platforms or
 * other decorated platforms to allow for platforms that "break" when the
 * fighter jumps and collides into the bottom of the platform. Upon breaking the
 * platform will release a power up or other bonus object that was stored in
 * this class.
 * 
 * @author Nick Gordon
 */
public class BreakablePlatform extends DecoratedPlatform implements
		JsonableSprite {

	private static final long serialVersionUID = 1254073087890380273L;
	private List<BonusObject> myBonusObjects = new ArrayList<BonusObject>();
	int numHitsToBreak = Integer.parseInt(myPlatformResources
			.getString("DefaultNumHitsToBreak"));

	/**
	 * Constructor for a breakable platform
	 * 
	 * @param decoratorComponent
	 *            platform that this class will be decorating
	 */
	public BreakablePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
	}

	public void setNumHitsToBreak(int numHits) {
		numHitsToBreak = numHits;
	}

	/**
	 * Function defining the behavior of this platform. Since this function is
	 * called in update and the behavior of this platform depends on a collision
	 * manager the behavior of this platform is implemented elsewhere.
	 */
	@Override
	public void doBehavior(double speed, double distance) {
		return;
	}

	/**
	 * Allows the user to define what bonus object should be stored in the
	 * myBonusObject field. This is the object released when the platform
	 * "breaks"
	 * 
	 * @param bonusObject
	 *            BonusObject that will be stored in the myBonusObject field.
	 */
	public void addBonusObject(BonusObject bonusObject) {
		bonusObject.setActive(false);
		myBonusObjects.add(bonusObject);

	}

	/**
	 * Function that releases the bonus object stored in myBonusObject field. It
	 * sets the bonus object to active, gives it the location of the platform
	 * and sets its horizontal speed to 0.01 so that it moves slowly once
	 * released.
	 */
	protected void releaseItem(int index) {
		if (myBonusObjects != null && myBonusObjects.size() > index) {
			BonusObject object = myBonusObjects.get(index);
			object.setActive(true);
			object.setLocation(getX(), getY() +  Integer.parseInt(myPlatformResources.getString("PlatformOffset")));
			object.setHorizontalSpeed(Double.parseDouble(myPlatformResources
					.getString("BonusObjectSpeed")));
			myBonusObjects.remove(index);
		}
	}

	/**
	 * Releases all bonus objects associated with this breakable platform
	 */
	protected void releaseAllItems() {
		double speed = Integer.parseInt(myPlatformResources
				.getString("BonusObjectSpeed"));
		for (BonusObject object : myBonusObjects) {
			object.setActive(true);
			object.setLocation(getX(), getY());
			object.setHorizontalSpeed(speed);
			speed += Integer.parseInt(myPlatformResources
					.getString("BonusObjectSpeed"));
		}
	}

	/**
	 * Releases a random bonus object associated with this breakable platform
	 */
	protected void releaseRandomItem() {
		Random rand = new Random();
		if (myBonusObjects.size() != 0) {
			int index = rand.nextInt(myBonusObjects.size());
			releaseItem(index);
		}
	}

	/**
	 * Implements the functionality of the full behavior of this platform. Once
	 * the platform is hit on the bottom by a fighter it calls release item so
	 * that the bonus object stored in the platform is released and then sets
	 * the platform to a non-active state since it is now destroyed by the
	 * fighter. This method should only be called in the collision manager for
	 * fighters and breakable platforms.
	 */

	public void doBreak() {
		releaseRandomItem();
		numHitsToBreak--;
		System.out.println(numHitsToBreak);
		if (numHitsToBreak == 0) {
			setActive(false);
			setLocation(-1000, getY()); 
		}
	}

	/**
	 * Used in saving platforms in the level editor this method creates a string
	 * representing this platform class as well as the platforms that it
	 * decorates.
	 */
	@Override
	public String toString() {
		return myPlatformResources.getString("Breakable")
				+ myDecoratorComponent.toString();
	}

	@Override
	public Object clone() {
		AbstractPlatform toWrap = null;
		toWrap = (AbstractPlatform) myDecoratorComponent.clone();
		return new BreakablePlatform(toWrap);
	}

	public void actionBreak(AnimatedGameSprite sprite,
			CollisionContext ccntext, CollisionSpec cspec) {
		if (ccntext.getSide() == CollisionGroup.BOTTOM_TOP_COLLISION) {
			System.out.println("Break is not working!");
			((BreakablePlatform) sprite).doBreak();
		}
	}

	private BreakablePlatform() {
	};

	public static SpriteFactory<BreakablePlatform> getFactory() {
		return new SpriteFactory<BreakablePlatform>(new BreakablePlatform());
	}

}
