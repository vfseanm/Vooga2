package platforms.platformtypes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import platforms.FrameTimer;

public abstract class DecoratedPlatform extends AbstractPlatform {

	private static final long serialVersionUID = -9022534130487528963L;
	protected AbstractPlatform myDecoratorComponent;
	protected double mySpeed = 1;
	protected double myDistance = 100;
	FrameTimer myTimer = new FrameTimer();

	public DecoratedPlatform(AbstractPlatform decoratorComponent) {
		myDecoratorComponent = decoratorComponent;
	}

	public void setHorizontalSpeed(double speed) {
		myDecoratorComponent.setHorizontalSpeed(speed);
	}

	public void setVerticalSpeed(double speed) {
		myDecoratorComponent.setVerticalSpeed(speed);
	}

	public void update(long elapsedTime) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.update(elapsedTime);
		}
		doBehavior(mySpeed, myDistance);
		super.update(elapsedTime);
	}

	public void setAnimate(boolean arg) {
		myDecoratorComponent.setAnimate(arg);
	}

	public void setFrame(int frame) {
		myDecoratorComponent.setFrame(frame);
	}

	public void setLocation(double x, double y) {
		myDecoratorComponent.setLocation(x, y);
	}

	public double getX() {
		return myDecoratorComponent.getX();
	}

	public double getY() {
		return myDecoratorComponent.getY();
	}

	public void setX(double x) {
		myDecoratorComponent.setX(x);

	}

	public void setY(double y) {
		myDecoratorComponent.setY(y);

	}

	public BufferedImage[] getImages() {
		return myDecoratorComponent.getImages();
	}

	public void setImages(BufferedImage[] images) {
		myDecoratorComponent.setImages(images);
	}

	public List<String> getImageNames() {
		return myDecoratorComponent.getImageNames();
	}

	public void render(Graphics2D graphics) {
		myDecoratorComponent.render(graphics);
	}

	protected void releaseItem() {
		myDecoratorComponent.releaseItem();
	}

	protected void doBreak() {
		myDecoratorComponent.doBreak();
	}

	public void setActive(boolean arg) {
		myDecoratorComponent.setActive(arg);
	}

	public int getHeight() {
		return myDecoratorComponent.getHeight();
	}

	public int getWidth() {
		return myDecoratorComponent.getWidth();
	}

}
