package platforms.fsmframework;

import java.util.ArrayList;

import java.util.List;


import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.UpDownPlatform;

public class UpDownState extends AbstractPlatformState {


	private List<AbstractPlatform> newPlatforms = new ArrayList<AbstractPlatform>();
	
	
	public UpDownState() {}
	
	public void decoratePlatforms() {
		//newPlatforms = new ArrayList<AbstractPlatform>();
		for (AbstractPlatform platform : myControlledPlatforms) {
			newPlatforms.add(new UpDownPlatform(platform));
		}
	}

	@Override
	public void handle(long elapsedTime) {
		for (AbstractPlatform platform : newPlatforms) {
			platform.update(elapsedTime);	
			//System.out.println(platform.toString());
			//System.out.println("BUZZ");
		}
	}
}


