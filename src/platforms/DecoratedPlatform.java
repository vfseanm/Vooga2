package platforms;

import java.awt.Graphics2D;

public abstract class DecoratedPlatform extends AbstractPlatform {
	 
	private static final long serialVersionUID = -9022534130487528963L;
	protected AbstractPlatform myDecoratorComponent;
	protected double mySpeed = 1;
	protected double myDistance = 100;
	FrameTimer myTimer = new FrameTimer();
	
	public DecoratedPlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent.getImages(), decoratorComponent.getX(), decoratorComponent.getY(), decoratorComponent.getImageNames(), null);
		myDecoratorComponent = decoratorComponent;
		/*setX(myDecoratorComponent.getX());
		setY(myDecoratorComponent.getY());
		setImages(myDecoratorComponent.getImages());
		setImageNames(myDecoratorComponent.getImageNames());*/
	}
	
	public void setSpeed(double speed) {
		mySpeed = speed;
	}
	
	public void setDistance(double distance) {
		myDistance = distance;
	}
	
	public void setHorizontalSpeed(double speed) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.setHorizontalSpeed(speed);
		}
		super.setHorizontalSpeed(speed);
		
	}
	
	public void setVerticalSpeed(double speed) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.setVerticalSpeed(speed);
		}
		super.setVerticalSpeed(speed);		
	}
	
	public void moveAll(double x, double y) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.moveAll(x, y);
		}
		move(x, y);		
	}
	
	public void update(long elapsedTime) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.update(elapsedTime);
		}
		doBehavior(mySpeed, myDistance);
		super.update(elapsedTime);
	}
	
	public void setAnimate(boolean arg) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.setAnimate(arg);
		}
		super.setAnimate(arg);
	}
	
	public void setFrame(int frame) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.setFrame(frame);
		}
		super.setFrame(frame);
	}
	
	public void setLocation(double x, double y) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.setLocation(x, y);
		}
		super.setLocation(x, y);
	}
	

	//works in tester game....something funky in level editor/demo game makes null pter exception here....
 /* public void render(Graphics2D graphics) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.render(graphics);	

		}
	}*/
}
