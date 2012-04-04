package platforms;
import java.awt.image.BufferedImage;

import sprite.GameSprite;

@SuppressWarnings("serial")
public abstract class AbstractPlatform extends GameSprite {

	protected AbstractPlatform(BufferedImage im, double x, double y, String image) {
		super(im, x, y, image);
	}
	
	public abstract void doBehavior();
	public abstract AbstractPlatform getNextState();

}
