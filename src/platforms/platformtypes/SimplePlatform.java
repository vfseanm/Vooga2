package platforms.platformtypes;

import java.util.ArrayList;
import java.util.List;

public class SimplePlatform extends AbstractPlatform {
	
	private static final long serialVersionUID = 7514750773895804951L;

	public SimplePlatform(double x, double y, List<String> imageNames) {
	    super(x, y, imageNames);
	}

	@Override
	protected void doBehavior(double speed, double distance) {
		return;	
	}
	

	
	@Override
	public String toString() {
		return myPlatformResources.getString("Simple");
	}

	 public Object clone() {
	        List<String> imageNames = new ArrayList<String>();
	        imageNames.addAll(this.getImageNames());
	        SimplePlatform e = new SimplePlatform(this.getX(), this.getY(),imageNames);
	        return e;
	}



}
