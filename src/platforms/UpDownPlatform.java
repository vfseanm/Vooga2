package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import sprite.Fighter;

@SuppressWarnings("serial")
public class UpDownPlatform extends DecoratedPlatform {
	
	FrameTimer myTimer = new FrameTimer();

	public UpDownPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter) {
		super(im, x, y, images, fighter);
	}
	
	public UpDownPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter, AbstractPlatform decoratorComponent) {
          super(im, x, y, images, fighter, decoratorComponent);
      }

	
	public void doBehavior(double speed, double distance) {
		
		if (myDecoratorComponent != null) {
            		myDecoratorComponent.doBehavior(speed, distance);
        	}
		double time = (distance * 5)/speed;
		if (myTimer.getElapsedTime() % (time*2) == 0) {
			setVerticalSpeed(speed/25);
		}
		else if (myTimer.getElapsedTime() % time == 0) {
			setVerticalSpeed(-speed/25);
		}
		myTimer.update();
	}	
	
	public void update(long elapsedTime) {
		
		if (myDecoratorComponent != null) {
            		myDecoratorComponent.update(elapsedTime);
        	}
	}
}
