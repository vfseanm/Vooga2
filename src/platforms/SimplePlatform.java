package platforms;
import java.awt.image.BufferedImage;
import java.util.List;
import fighter.Fighter;

public class SimplePlatform extends AbstractPlatform {
	
	private static final long serialVersionUID = 7514750773895804951L;

	public SimplePlatform(BufferedImage[] im, double x, double y, List<String> images) {
	    super(im, x, y, images);
	}

	@Override
	protected void doBehavior(double speed, double distance) {
		return;	
	}
	
	@Override
	public String toString() {
		return "platform";
	}
}
