package platforms.fsmframework;

import java.util.ArrayList;
import java.util.List;

import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.UpDownPlatform;

public class SwitchOn extends AbstractPlatformState {

	
	public SwitchOn(List<AbstractPlatform> platforms) {
		super(platforms);	
	}

	private List<AbstractPlatform> newPlatforms = new ArrayList<AbstractPlatform>();
	
	public void thing() {
		for (AbstractPlatform platform : myControlledPlatforms) {
			newPlatforms.add(new UpDownPlatform(platform));
		
		}
	}

	@Override
	public void handle(long elapsedTime) {
		for (AbstractPlatform platform : newPlatforms) {
			platform.update(elapsedTime);	
			System.out.println(platform.toString());
			System.out.println("BUZZ");
		}
	}
}


