package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import fighter.*;


@SuppressWarnings("serial")
public class UpDownPlatform extends DecoratedPlatform
{

    FrameTimer myTimer = new FrameTimer();

    public UpDownPlatform (AbstractPlatform decoratorComponent)
    {
        super(decoratorComponent);
        setX(decoratorComponent.getX());
        setY(decoratorComponent.getY());
    }


    public void doBehavior (double speed, double distance)
    {

        if (myDecoratorComponent != null)
        {
            myDecoratorComponent.doBehavior(speed, distance);
        }
        double time = (distance * 5) / speed;
        if (myTimer.getElapsedTime() % (time * 2) == 0)
        {
            setVerticalSpeed(speed / 25);
        }
        else if (myTimer.getElapsedTime() % time == 0)
        {
            setVerticalSpeed(-speed / 25);
        }
        myTimer.update();
    }


    public void update (long elapsedTime)
    {

        if (myDecoratorComponent != null)
        {
            myDecoratorComponent.update(elapsedTime);
        }
    }
    
    public String toString()
    {
        return "up and down " + myDecoratorComponent.toString();
    }
}
