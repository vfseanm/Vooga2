package platforms;

import powerUps.PowerUp;

public class BreakablePlatformItemFactory {
	private PowerUp myItem;
	
	public BreakablePlatformItemFactory(PowerUp item) {
		myItem = item;
	}
	
	public PowerUp getItem(double x, double y) {
		return myItem.makeItem(x, y);
	}
}
