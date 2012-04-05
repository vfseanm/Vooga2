package platforms;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import sprite.AnimatedGameSprite;

@SuppressWarnings("serial")
public abstract class AbstractPlatform extends AnimatedGameSprite {

	protected AbstractPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images) {
		super(im, x, y, images);
	}
	
	//public abstract void doBehavior();
	//public abstract AbstractPlatform getNextState();

}
