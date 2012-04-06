package platforms;

import java.awt.image.BufferedImage;

import java.util.ArrayList;

import sprite.Fighter;

@SuppressWarnings("serial")
public class RotatingPlatform extends DecoratedPlatform {

    double mySpeed = -1;
    int myHeight;
    int myWidth;
    int myFrames;
    FrameTimer myTimer = new FrameTimer();

    public RotatingPlatform(BufferedImage[] im, double x, double y,
            ArrayList<String> images, Fighter fighter) {
        super(im, x, y, images, fighter);
        setLoopAnim(true);
        myHeight = im[0].getHeight();
        myWidth = im[0].getWidth();
        // setAnimate(false);
    }
    
     public RotatingPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter, AbstractPlatform decoratorComponent) {
          super(im, x, y, images, fighter. decoratorComponent);
      }

    // TODO: distance needed to conform to abstractPlatform
    // specifications...will try to fix later
    public void doBehavior(int speed, double distance) {
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
        myTimer.update();
        rotateCenterAxis(50); //test
    }

}
