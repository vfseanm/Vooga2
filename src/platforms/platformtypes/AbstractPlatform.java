package platforms.platformtypes;
import java.awt.image.BufferedImage;
import java.util.List;
import fighter.Fighter;
import sprite.AnimatedGameSprite;

public abstract class AbstractPlatform extends AnimatedGameSprite {

	private static final long serialVersionUID = 1483938382856783084L;
	
	protected AbstractPlatform(BufferedImage[] images, double x, double y, List<String> imageSources) {
		super(images, x, y, imageSources);
	}
	
	protected AbstractPlatform() {}
	
	protected abstract void doBehavior(double speed, double distance);
	
	protected abstract void releaseItem();

}
