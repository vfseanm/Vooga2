package platforms;
import sprite.GameSprite;

//this class is a factory that allows the creation of power-ups/coins 
//when a platform is hit and broken...
public class BreakablePlatformObjectFactory {
	private GameSprite myObject;
	
	public BreakablePlatformObjectFactory(GameSprite object) {
		myObject = object;
	}
	
	public GameSprite getObject() {
		return myObject;
	}	                                                      
}