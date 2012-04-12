package platforms;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import fighter.*;


@SuppressWarnings("serial")
public class SideToSidePlatform extends DecoratedPlatform
{

    FrameTimer myTimer = new FrameTimer();
    double mySpeed = 10;
    double myDistance = 10;


    public SideToSidePlatform (AbstractPlatform decoratorComponent)
    {
        super(decoratorComponent);
    }
    
    public void setSpeed(double speed) {
    	mySpeed = speed;
    }
    
    public void setDistance(double distance) {
    	myDistance = distance;
    }


    protected void doBehavior (double speed, double distance)
    {
        if (myDecoratorComponent != null)
        {
            myDecoratorComponent.doBehavior(speed, distance);
        }
        double time = (distance * 5) / speed;
        if (myTimer.getElapsedTime() % (time * 2) == 0)
        {
            setHorizontalSpeed(speed / 25);
        }
        else if (myTimer.getElapsedTime() % time == 0)
        {
            setHorizontalSpeed(-speed / 25);
        }
    }


    public void update (long elapsedTime)
    {

        if (myDecoratorComponent != null) {
            myDecoratorComponent.update(elapsedTime);
        }
        doBehavior(mySpeed, myDistance);
        myTimer.update(elapsedTime);
        update(elapsedTime);
    }
    
    public void render(Graphics2D graphics) {
    	if (myDecoratorComponent != null) {
    		myDecoratorComponent.render(graphics);
    	}
    	render(graphics);
    }
    
    public String toString()
    {
        return "side to side " + myDecoratorComponent.toString();
    }
}
