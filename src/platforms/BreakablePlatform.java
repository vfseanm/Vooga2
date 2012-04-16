package platforms;
import java.util.ArrayList;

import java.util.Random;

import bonusobjects.PowerUp;




public class BreakablePlatform extends DecoratedPlatform {

	private static final long serialVersionUID = 1254073087890380273L;
	ArrayList<BreakablePlatformItemFactory> myFactoryList = new ArrayList<BreakablePlatformItemFactory>();
	
	public BreakablePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
	}
	
	public void doBehavior(double speed, double distance) {
		return;
	}
	
	public void doBreak() {
		//only called if colliding top/bottom with fighter
		releaseItem();
		setActive(false);
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
