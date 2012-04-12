package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import collisions.FighterBreakablePlatformCollisionManager;
import powerUps.PowerUp;
import fighter.*;

@SuppressWarnings("serial")
public class BreakablePlatform extends DecoratedPlatform {
	
	public BreakablePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		// TODO Auto-generated constructor stub
	}

	ArrayList<BreakablePlatformItemFactory> myFactoryList = new ArrayList<BreakablePlatformItemFactory>();
	FighterBreakablePlatformCollisionManager myCollisionManager = new FighterBreakablePlatformCollisionManager();

	public void update(long elapsedTime) {
		
		if (myDecoratorComponent != null) {
            		myDecoratorComponent.update(elapsedTime);
        	}
	    myCollisionManager.checkCollision();
	}
	
	public void addItem(BreakablePlatformItemFactory factory) {
		myFactoryList.add(factory);
	}
	
	public PowerUp releaseItem() {
		Random chooser = new Random();
		BreakablePlatformItemFactory factory = myFactoryList.get(chooser.nextInt(myFactoryList.size()));
		return factory.getItem(getX(), getY());
	}
	
	//TODO: refused bequest on parameters...will fix later...
	public void doBehavior(double speed, double distance) {
		if (myDecoratorComponent != null) {
            		myDecoratorComponent.doBehavior(speed, distance);
        	}
		setFrame(1);
		setActive(false);
		
		
	}
}
