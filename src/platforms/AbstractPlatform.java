package platforms;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import characters.fighter.*;
import sprite.AnimatedGameSprite;

public abstract class AbstractPlatform extends AnimatedGameSprite {

	private static final long serialVersionUID = 1483938382856783084L;
	Fighter myFighter;
	
	protected AbstractPlatform(BufferedImage[] images, double x, double y, List<String> imageSources, Fighter fighter) {
		super(images, x, y, imageSources);
		myFighter = fighter;
	}
	
	protected AbstractPlatform() {}
	
	protected abstract void doBehavior(double speed, double distance);
	public abstract void updateAll(long elapsedTime);
	public abstract void renderAll(Graphics2D graphics);
	public abstract void setAllHorizontalSpeed(double speed);
	public abstract void moveAll(double x, double y);

}
