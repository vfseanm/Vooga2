package platforms;

import java.awt.image.BufferedImage;

public class RotatingPlatform extends DecoratedPlatform {
	
	private static final long serialVersionUID = 7537483544825845415L;
	int myFrames = 0;
	int myWidth;
	int myHeight;
	int myDelay = 100;
	
	public RotatingPlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		setLoopAnim(true);
		myHeight = getImages()[0].getHeight();
		myWidth = getImages()[0].getWidth();
	}
	
	protected void doBehavior(double speed, double distance) {
		
		if (!isAnimate()) {
			//setAnimate(true);
		}
		//System.out.println(getImages().length);
		System.out.println(getFrame());
		rotateLeftAxisClockwise(myDelay);
	}
	
	public void rotateCenterAxis(int delay) {
		myTimer.update();
		System.out.println(myTimer.getPassedFrames() % delay == 0);
		if (myTimer.getPassedFrames() % delay == 0) {
			//updateAnimation();
			myFrames++;
			if (myFrames % 2 == 0) {
				setLocation(getX() - myWidth / 2.0 + myHeight / 2.0, getY() + myWidth / 2.0 - myHeight / 2.0);
				setFrame(0);
			}
			else if (myFrames % 2 == 1) {
				setLocation(getX() + myWidth / 2.0 - myHeight / 2.0, getY() - myWidth/ 2.0 + myHeight / 2.0);
				setFrame(1);
			}
		}
	}
	
	  public void rotateLeftAxisClockwise(int delay) {
	        myTimer.update();

	        if (myTimer.getPassedFrames() % delay == 0) {
	        	//updateAnimation();
	            myFrames++;

	            if (myFrames % 4 == 0) {
	                setLocation(getX(), getY() + myWidth - myHeight);
	                setFrame(0);
	                // System.out.println("animate1");
	            }

	            else if (myFrames % 4 == 1) {
	            	setFrame(1);
	                // System.out.println("animate2");
	            }

	            else if (myFrames % 4 == 2) {
	            	setFrame(0);
	                setLocation(getX() - myWidth + myHeight, getY());
	                // System.out.println("animate3");
	            }

	            else if (myFrames % 4 == 3) {
	            	setFrame(1);
	                setLocation(getX() + myWidth - myHeight, getY() - myWidth + myHeight);
	                // System.out.println("animate4");
	            }

	        }
	    }
	
	public String toString() {
		return "rotating" + myDecoratorComponent.toString();
	}

	
}
