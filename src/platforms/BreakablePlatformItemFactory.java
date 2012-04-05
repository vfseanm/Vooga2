package platforms;
import powerUps.PowerUp;

//this class is a factory that allows the creation of power-ups/coins 
//when a platform is hit and broken...
public class BreakablePlatformItemFactory {
	private PowerUp myItem;
	
	public BreakablePlatformItemFactory(PowerUp item) {
		myItem = item;
	}
	
	public PowerUp getItem() {
		return myItem;
	}	                                                      
}
