package platforms.platformtypes;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;

import platforms.FrameTimer;

/**
 * Abstract class that defines functionality for any platforms that are used to
 * decorate other platforms. However, it is mostly used to extract common
 * functionality from platform decorator subclasses since all of these classes
 * implement identical functions to override functions inherited from
 * AnimatedGameSprite such that these functions can call the function on their
 * decorator components as well and eventually call the functions in the simple
 * platform that it decorates and which contains the data for the platform. In
 * this way, these classes can add functionality to other platforms without
 * having to deal with any of the data associated with them.
 * 
 * @author Nick Gordon
 * 
 */
public abstract class DecoratedPlatform extends AbstractPlatform {

	private static final long serialVersionUID = -9022534130487528963L;
	protected AbstractPlatform myDecoratorComponent;
	protected double mySpeed;
	protected double myDistance;
	protected int myDelay = Integer.parseInt(myPlatformResources
			.getString("RotateDelay"));;
	protected int mySpeedOffset = Integer.parseInt(myPlatformResources
			.getString("SpeedOffset"));;
	protected int myDistanceOffset = Integer.parseInt(myPlatformResources
			.getString("DistanceOffset"));;

	FrameTimer myTimer = new FrameTimer();

	/**
	 * General constructor for a platform decorator
	 * 
	 * @param decoratorComponent
	 *            the platform that the class will be decorating
	 */
	public DecoratedPlatform(AbstractPlatform decoratorComponent) {
		myDecoratorComponent = decoratorComponent;
		/*
		 * myDelay = Integer.parseInt(myPlatformResources
		 * .getString("RotateDelay")); mySpeedOffset =
		 * Integer.parseInt(myPlatformResources .getString("SpeedOffset"));
		 * myDistanceOffset = Integer.parseInt(myPlatformResources
		 * .getString("DistanceOffset"));
		 */

	}

	public DecoratedPlatform() {
	}

	/**
	 * Used to set the delay of certain platforms. For example, the higher the
	 * delay set for a rotating platform decorator the slower that platform will
	 * rotate.
	 * 
	 * @param delay
	 *            an integer representing how slow or fast certain platform
	 *            should move
	 */
	public void setDelay(int delay) {
		myDelay = delay;
	}

	/**
	 * Used to set the movement speed of certain platforms. For example, the
	 * higher the speed is set the faster a sidetoside platform will move.
	 * 
	 * @param speed
	 *            double representing move speed of certain platforms.
	 */
	public void setSpeed(double speed) {
		mySpeed = speed;
	}

	/**
	 * Used to set the movement range of certain decorated platforms. For
	 * example, the SideToSidePlatform will only allow a platform to move back
	 * and forth across a distance defined by the distance field which
	 * represents distance based upon pixels
	 * 
	 * @param distance
	 *            double representing the movement range of certain platforms in
	 *            pixels
	 */
	public void setDistance(double distance) {
		myDistance = distance;
	}

	/**
	 * Sets horizontal speed of the sprite, the speed is based on actual time in
	 * milliseconds, 1 means the sprite is moving as far as 1000 (1x1000ms)
	 * pixels in a second to the bottom, while negative value (-1) means the
	 * sprite is moving to the left.
	 * 
	 * @param speed
	 *            double representing the speed of the sprite
	 */
	@Override
	public void setHorizontalSpeed(double speed) {
		myDecoratorComponent.setHorizontalSpeed(speed);
	}

	/**
	 * Sets vertical speed of the platform, the speed is based on actual time in
	 * milliseconds, 1 means the sprite is moving as far as 1000 (1x1000ms)
	 * pixels in a second to the bottom, while negative value (-1) means the
	 * sprite is moving to the top.
	 * 
	 * @param speed
	 *            double representing the speed of the sprite
	 */
	@Override
	public void setVerticalSpeed(double speed) {
		myDecoratorComponent.setVerticalSpeed(speed);
	}

	/**
	 * Updates this sprite as well as the platform components in the sprite.
	 * This includes making the platform call doBehavior thus implementing its
	 * defined behavior
	 * 
	 * @elapsedTime long value representing the elapsed time of the game
	 */
	@Override
	public void update(long elapsedTime) {
		myDecoratorComponent.update(elapsedTime);
		doBehavior(mySpeed, myDistance);
		super.update(elapsedTime);
	}

	/**
	 * Sets true to animate the platform
	 * 
	 * @param arg
	 *            boolean used to determine whether to animate the platform or
	 *            not
	 */
	@Override
	public void setAnimate(boolean arg) {
		myDecoratorComponent.setAnimate(arg);
	}

	/**
	 * Sets current animated frame of the simple platform that this platfor is
	 * decorating. For example, if set to frame 1 the image at index one in the
	 * array of images for the platform will be used to show the sprite in the
	 * game.
	 * 
	 * @param frame
	 *            int representing index of image used to represent sprite
	 */
	@Override
	public void setFrame(int frame) {
		myDecoratorComponent.setFrame(frame);
	}

	/**
	 * Sets the x and y position of the simple platform that this platform is
	 * decorating.
	 * 
	 * @param x
	 *            double used to represent x position of the platform
	 * @param y
	 *            double sued to represent y position of the platform
	 */
	@Override
	public void setLocation(double x, double y) {
		myDecoratorComponent.setLocation(x, y);
	}

	public Background getBackground() {
		return myDecoratorComponent.getBackground();
	}

	public double getScreenX() {
		return myDecoratorComponent.getScreenX();
	}

	public double getScreenY() {
		return myDecoratorComponent.getScreenY();
	}

	public double getOldX() {
		return myDecoratorComponent.getOldX();
	}

	public double getOldY() {
		return myDecoratorComponent.getOldY();
	}

	public double getVerticalSpeed() {
		return myDecoratorComponent.getVerticalSpeed();
	}

	public double getHorizontalSpeed() {
		return myDecoratorComponent.getHorizontalSpeed();
	}

	public double getDistance(Sprite other) {
		return myDecoratorComponent.getDistance(other);
	}

	public void forceY(double ys) {
		myDecoratorComponent.forceY(ys);
	}

	public void forceX(double xs) {
		myDecoratorComponent.forceX(xs);
	}

	public void addHorizontalSpeed(long elapsedTime, double accel,
			double maxSpeed) {
		myDecoratorComponent.addHorizontalSpeed(elapsedTime, accel, maxSpeed);
	}

	public void addVerticalSpeed(long elapsedTime, double accel, double maxSpeed) {
		myDecoratorComponent.addVerticalSpeed(elapsedTime, accel, maxSpeed);
	}

	public Object getDataID() {
		return myDecoratorComponent.getDataID();
	}

	public int getID() {
		return myDecoratorComponent.getID();
	}

	public int getLayer() {
		return myDecoratorComponent.getLayer();
	}

	public boolean isActive() {
		return myDecoratorComponent.isActive();
	}

	public boolean isImmutable() {
		return myDecoratorComponent.isImmutable();
	}

	public boolean isOnScreen() {
		return myDecoratorComponent.isOnScreen();
	}

	public boolean isOnScreen(int leftOffset, int topOffset, int rightOffset,
			int bottomOffset) {
		return myDecoratorComponent.isOnScreen(leftOffset, topOffset,
				rightOffset, bottomOffset);
	}

	public void move(double dx, double dy) {
		myDecoratorComponent.move(dx, dy);
	}

	public boolean moveTo(long elapsedTime, double xs, double ys, double speed) {
		return myDecoratorComponent.moveTo(elapsedTime, xs, ys, speed);
	}

	public void moveX(double dx) {
		myDecoratorComponent.moveX(dx);
	}

	public void moveY(double dy) {
		myDecoratorComponent.moveY(dy);
	}

	public void render(Graphics2D graphics, int x, int y) {
		myDecoratorComponent.render(graphics, x, y);
	}

	public void setBackground(Background backgr) {
		myDecoratorComponent.setBackground(backgr);
	}

	public void setImmutable(boolean b) {
		myDecoratorComponent.setImmutable(b);
	}

	public void setLayer(int i) {
		myDecoratorComponent.setLayer(i);
	}

	public void setMovement(double speed, double angleDir) {
		myDecoratorComponent.setMovement(speed, angleDir);
	}

	/*
	 * protected void updateMovement(long elapsedTime) {
	 * myDecoratorComponent.updateMovement(elapsedTime); }
	 */

	/**
	 * Gets the x position of the platform stored in simple platform
	 * 
	 * @return x position of the platform
	 */
	@Override
	public double getX() {
		return myDecoratorComponent.getX();
	}

	/**
	 * gets the y position of the platform stored in simple platform
	 * 
	 * @return y position of the platform
	 */
	@Override
	public double getY() {
		return myDecoratorComponent.getY();
	}

	/**
	 * Sets the x position of the platform
	 * 
	 * @param x
	 *            double representing x position of the platform
	 */
	@Override
	public void setX(double x) {
		myDecoratorComponent.setX(x);
	}

	/**
	 * Sets the y position of the platform
	 * 
	 * @param y
	 *            double representing y position of the platform
	 */
	@Override
	public void setY(double y) {
		myDecoratorComponent.setY(y);

	}

	public BufferedImage getImage() {
		return myDecoratorComponent.getImage();
	}

	/**
	 * Get array of images associated with the platform and which is stored in
	 * the simple platform that this class decorates
	 * 
	 * @return array of images associated with the platform
	 */
	@Override
	public BufferedImage[] getImages() {
		return myDecoratorComponent.getImages();
	}

	/**
	 * Sets the array of images associated with platform and which is stored in
	 * the simple paltform that this class decorates
	 * 
	 * @param images
	 *            BufferedImage array representing images for the platform
	 */
	@Override
	public void setImages(BufferedImage[] images) {
		myDecoratorComponent.setImages(images);
	}

	/**
	 * gets list of all filenames for the images associated with the platform
	 * 
	 * @return list of strings representing filenames for the images associated
	 *         with the platform
	 */
	@Override
	public List<String> getImageNames() {
		return myDecoratorComponent.getImageNames();
	}

	/**
	 * Renders the platform. More specifically, only renders the simple platform
	 * that this class decorates
	 * 
	 * @param graphics
	 *            Graphics2D representing the graphics of the game
	 */
	@Override
	public void render(Graphics2D graphics) {
		myDecoratorComponent.render(graphics);
	}

	/*
	 * protected void releaseItem() { myDecoratorComponent.releaseItem(); }
	 * 
	 * protected void doBreak() { myDecoratorComponent.doBreak(); }
	 */

	/**
	 * Sets the platform to active allowing it to be rendered
	 * 
	 * @param arg
	 *            boolean representing whether or not the platform should be
	 *            active (true) or inactive (false).
	 */
	@Override
	public void setActive(boolean arg) {
		myDecoratorComponent.setActive(arg);
	}

	/**
	 * Gets the height of platform, more specifically height of the image
	 * representing it
	 * 
	 * @return int representing height of the platform
	 */
	@Override
	public int getHeight() {
		return myDecoratorComponent.getHeight();
	}

	/**
	 * Gets the width of platform, more specifically width of the image
	 * representing it
	 * 
	 * @return int representing width of the platform
	 */
	@Override
	public int getWidth() {
		return myDecoratorComponent.getWidth();
	}

	@SuppressWarnings("rawtypes")
	public List<Class> getClassesOfDecorators() {
		List<Class> classList = new ArrayList<Class>();
		classList.add(this.getClass());
		if (!myDecoratorComponent.getClass().equals(SimplePlatform.class)) {
			classList.addAll(((DecoratedPlatform) myDecoratorComponent)
					.getClassesOfDecorators());
		}
		return classList;
	}

}
