package platforms.fsmframework;
import java.util.ArrayList;
import java.util.List;
import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.SimplePlatform;


public class SwitchOff extends AbstractPlatformState {

	public SwitchOff(List<AbstractPlatform> platforms) {
		super(platforms);
	}
	
	private List<AbstractPlatform> newPlatforms = new ArrayList<AbstractPlatform>();
	
	//from constructor
	public void thing() {
		System.out.println(myControlledPlatforms);
		for (AbstractPlatform platform : myControlledPlatforms) {
			newPlatforms.add(new SimplePlatform(platform.getX(), platform.getY(), platform.getImageNames()));
			
			//System.out.println(platform.toString());
		}
	}
	
	@Override
 	public void handle(long elapsedTime) {
		for (AbstractPlatform platform : newPlatforms) {
			platform.update(elapsedTime);	
	}
	
}
}
