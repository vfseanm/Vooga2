package platforms.platformtypes;
import java.awt.image.BufferedImage;
import java.util.List;
import sprite.AnimatedGameSprite;

public abstract class AbstractPlatform extends AnimatedGameSprite {

	private static final long serialVersionUID = 1483938382856783084L;
	
	protected AbstractPlatform(double x, double y, List<String> imageSources) {
		super( x, y, imageSources);
	}
	
	protected AbstractPlatform() {}
	
	protected abstract void doBehavior(double speed, double distance);
	
	protected abstract void releaseItem();
	
	protected abstract void doBreak();
	
	public abstract Object clone();

}
