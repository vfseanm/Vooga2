package platforms.platformtypes;

 import collisions.CustomActionPerformer;

import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;

 import bonusobjects.BonusObject;
 
/**
 * This class provides functionality that can be added on to simple platforms or
 * other decorated platforms to allow for platforms that "break" when the
 * fighter jumps and collides into the bottom of the platform. Upon breaking the
 * platform will release a power up or other bonus object that was stored in
 * this class.
 * 
 * @author yankeenjg
 */
public class BreakablePlatform extends DecoratedPlatform {

	private static final long serialVersionUID = 1254073087890380273L;
	private BonusObject myBonusObject; // need to fix in level editor so this
										// can be set...

	/**
	 * Constructor for a breakable platform
	 * 
	 * @param decoratorComponent
	 *            platform that this class will be decorating
	 */
	public BreakablePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
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
	public void setBonusObject(BonusObject bonusObject) {
		myBonusObject = bonusObject;
		myBonusObject.setActive(false);
	}

	/**
	 * Function that releases the bonus object stored in myBonusObject field. It
	 * sets the bonus object to active, gives it the location of the platform
	 * and sets its horizontal speed to 0.02 so that it moves slowly once
	 * released.
	 */
	private void releaseItem() {
		if (myBonusObject != null) {
			myBonusObject.setActive(true);
			myBonusObject.setLocation(getX(), getY() + 20);
			myBonusObject.setHorizontalSpeed(0.02);
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
		// only called if colliding top/bottom with fighter
		releaseItem();
		setActive(false);
	}
 	
	public void action (Enemy sprite1, int collisionType, CustomActionPerformer act){
		standardAction (sprite1, collisionType);
		if (collisionType == CollisionGroup.BOTTOM_TOP_COLLISION){
			this.doBreak();
		}
		customAction (sprite1, this, collisionType, act); 
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
	
	public static AbstractPlatform fromJson(String json)
	{
	    return AbstractPlatform.fromJson(json);
	}
}
