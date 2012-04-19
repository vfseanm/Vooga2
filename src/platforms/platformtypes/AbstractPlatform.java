package platforms.platformtypes;
import java.util.List;
import java.util.ResourceBundle;
import sprite.AnimatedGameSprite;

public abstract class AbstractPlatform extends AnimatedGameSprite {

	private static final long serialVersionUID = 1483938382856783084L;
	protected ResourceBundle myPlatformResources = ResourceBundle.getBundle("platforms.PlatformResourceBundle");
	
	protected AbstractPlatform(double x, double y, List<String> imageSources) {
		super(x, y, imageSources);
	}
	
	protected AbstractPlatform() {}
	
	protected abstract void doBehavior(double speed, double distance);
	
	public abstract Object clone();

}
