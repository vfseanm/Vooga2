package platforms.fsmframework;
import java.util.List;
import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.SimplePlatform;


public class SwitchOff extends AbstractPlatformState {

	public SwitchOff(List<AbstractPlatform> platforms) {
		super(platforms);
	}
	
	//from constructor
	public void thing() {
		System.out.println(myControlledPlatforms);
		for (AbstractPlatform platform : myControlledPlatforms) {
			platform = new SimplePlatform(platform.getX(), platform.getY(), platform.getImageNames());
		}
	}
	
	@Override
 	public void handle(long elapsedTime) {
		for (AbstractPlatform platform : myControlledPlatforms) {
			platform.update(elapsedTime);	
	}
	
}
}