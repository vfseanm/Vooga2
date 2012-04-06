package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import fighter.*;


@SuppressWarnings("serial")
public class SideToSidePlatform extends DecoratedPlatform
{

    FrameTimer myTimer = new FrameTimer();


    public SideToSidePlatform (BufferedImage[] im,
                               double x,
                               double y,
                               ArrayList<String> images,
                               Fighter fighter)
    {
        super(im, x, y, images, fighter);
    }


//	public SideToSidePlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter, AbstractPlatform decoratorComponent) {
//          super(im, x, y, images, fighter, decoratorComponent);
//      }

    public SideToSidePlatform (AbstractPlatform decoratorComponent)
    {
        super(decoratorComponent);
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
            setHorizontalSpeed(speed / 25);
        }
        else if (myTimer.getElapsedTime() % time == 0)
        {
            setHorizontalSpeed(-speed / 25);
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
}
