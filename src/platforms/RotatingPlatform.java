package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import fighter.*;

@SuppressWarnings("serial")
public class RotatingPlatform extends DecoratedPlatform {

    double mySpeed = -1;
    int myHeight;
    int myWidth;
    int myFrames;
    FrameTimer myTimer = new FrameTimer();
     
     public RotatingPlatform(AbstractPlatform decoratorComponent){
         super(decoratorComponent);
         setLoopAnim(true);
         setX(decoratorComponent.getX());
         setY(decoratorComponent.getY());
         myHeight = decoratorComponent.getImages()[0].getHeight();
         myWidth = decoratorComponent.getImages()[0].getWidth();
     }

    // TODO: distance needed to conform to abstractPlatform
    // specifications...will try to fix later
    public void doBehavior(double speed, double distance) {
        if (myDecoratorComponent != null) {
            myDecoratorComponent.doBehavior(speed, distance);
        }
        
        if (mySpeed != speed) {
            mySpeed = speed;
        }
        setAnimate(true);

    }
 

    public void rotateLeftAxisClockwise(int delay) {
        myTimer.update();

        if (myTimer.getElapsedTime() % delay == 0) {
            updateAnimation();
            myFrames++;

            if (myFrames % 4 == 0) {
                setLocation(getX(), getY() + myWidth - myHeight);
                // System.out.println("animate1");
            }

            else if (myFrames % 4 == 1) {
                // System.out.println("animate2");
            }

            else if (myFrames % 4 == 2) {
                setX(getX() - myWidth + myHeight);
                // System.out.println("animate3");
            }

            else if (myFrames % 4 == 3) {
                setLocation(getX() + myWidth - myHeight, getY() - myWidth
                        + myHeight);
                // System.out.println("animate4");
            }

        }
    }

    public void rotateCenterAxis(int delay) {
        myTimer.update();
        if (myTimer.getElapsedTime() % delay == 0) {
            updateAnimation();
            myFrames++;
            if (myFrames % 2 == 0) {
                setLocation(getX() - myWidth / 2.0 + myHeight / 2.0, getY()
                        + myWidth / 2.0 - myHeight / 2.0);
                //System.out.println("animate1");
            } else if (myFrames % 2 == 1) {
                setLocation(getX() + myWidth / 2.0 - myHeight / 2.0, getY()
                        - myWidth / 2.0 + myHeight / 2.0);
                //System.out.println("animate2");
            }
        }
    }
    
    public void rotateRightAxisClockwise(int delay) {
        //TODO: implement this method
    }

    public void update(long elapsedTime) {
        
    	if (myDecoratorComponent != null) {
            	myDecoratorComponent.update(elapsedTime);
        }
        myTimer.update();
        rotateCenterAxis(50); //test
    }

    public String toString()
    {
        return "rotating " + myDecoratorComponent.toString();
    }
}
