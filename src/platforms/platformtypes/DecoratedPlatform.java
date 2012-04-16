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
	
	public double getX() {
		if (myDecoratorComponent != null) {
			return myDecoratorComponent.getX();
		}
		return 0;
	}
	
	public double getY() {
		if (myDecoratorComponent != null) {
			return myDecoratorComponent.getY();
		}
		return 0;
	}
	
	public BufferedImage[] getImages() {
		if (myDecoratorComponent != null) {
			return myDecoratorComponent.getImages();
		}
		return null;
	}
	
	public void setImages(BufferedImage[] images)
	{
	    if(myDecoratorComponent!= null){
	        myDecoratorComponent.setImages(images);
	    }
	}
	public List<String> getImageNames(){
	    if(myDecoratorComponent != null)
	    {
	        return myDecoratorComponent.getImageNames();
	    }
	    return null;
	}

	//works in tester game....something funky in level editor broken
	public void render(Graphics2D graphics) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.render(graphics);	
		}
	}
	
	protected void releaseItem() {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.releaseItem();
		}
		return;
	}
	
	protected void doBreak() {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.doBreak();
		}
		return;
	}
	
	public void setActive(boolean arg) {
		if (myDecoratorComponent != null) {
			myDecoratorComponent.setActive(arg);
		}
	}
}
