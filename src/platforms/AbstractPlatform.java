package platforms;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import sprite.AnimatedGameSprite;
import sprite.Fighter;

@SuppressWarnings("serial")
public abstract class AbstractPlatform extends AnimatedGameSprite {
    
    Fighter myFighter;

	protected AbstractPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter) {
		super(im, x, y, images);
		myFighter = fighter;
	}
	
	//public abstract void doBehavior();
	//public abstract AbstractPlatform getNextState();

}
