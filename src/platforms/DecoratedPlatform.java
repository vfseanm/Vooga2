package platforms;

import java.awt.Graphics2D;

public abstract class DecoratedPlatform extends AbstractPlatform {
	 
	protected AbstractPlatform myDecoratorComponent;
	protected double mySpeed = 10;
	protected double myDistance = 10;
	FrameTimer myTimer = new FrameTimer();
	
	public DecoratedPlatform(AbstractPlatform decoratorComponent) {
		myDecoratorComponent = decoratorComponent;
		setX(myDecoratorComponent.getX());
		setY(myDecoratorComponent.getY());
		setImages(myDecoratorComponent.getImages());
		setImageNames(myDecoratorComponent.getImageNames());
	}
	
	public void setSpeed(double speed) {
		mySpeed = speed;
	}
	
	public void setDistance(double distance) {
		myDistance = distance;
	}
	
	public void updateAll(long elapsedTime) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.updateAll(elapsedTime);
		}
		doBehavior(mySpeed, myDistance);
	}
	
	public void renderAll(Graphics2D graphics) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.renderAll(graphics);
		}
		render(graphics);
	}
}
