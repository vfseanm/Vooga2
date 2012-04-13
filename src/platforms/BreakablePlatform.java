
package platforms;

import java.util.ArrayList;
import java.util.Random;

import com.golden.gamedev.object.SpriteGroup;

import powerups.PowerUp;



public class BreakablePlatform extends DecoratedPlatform {

	private static final long serialVersionUID = 1254073087890380273L;
	ArrayList<BreakablePlatformItemFactory> myFactoryList = new ArrayList<BreakablePlatformItemFactory>();
	
	CollisionGroupManager manager = new CollisionGroupManager();
	SpriteGroup sg = new SpriteGroup("plat");
	
	
	public BreakablePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		sg.add(this);
	}
	
	protected void doBehavior(double speed, double distance) {
		
		if (manager.isTopBottomCollisionCheck(myFighter, sg)) {
			releaseItem();
			setActive(false);
		}
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
