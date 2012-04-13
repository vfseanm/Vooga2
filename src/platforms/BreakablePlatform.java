
package platforms;

import java.util.ArrayList;
import java.util.Random;

import powerUps.PowerUp;

public class BreakablePlatform extends DecoratedPlatform {

	ArrayList<BreakablePlatformItemFactory> myFactoryList = new ArrayList<BreakablePlatformItemFactory>();
	
	
	public BreakablePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
	}
	
	
	protected void doBehavior(double speed, double distance) {
		//do some check collision thing
		//releaseItem();
		//setActive(false);
	}
	
	public PowerUp releaseItem() {
		Random chooser = new Random();
		BreakablePlatformItemFactory factory = myFactoryList.get(chooser.nextInt(myFactoryList.size()));
		return factory.getItem(getX(), getY());
	}
	
	public String toString() {
		return "side to side" + myDecoratorComponent.toString();
	}

	

}
