package platforms;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import fighter.Fighter;

public class SimplePlatform extends AbstractPlatform {
	
	   public SimplePlatform(BufferedImage[] im, double x, double y, List<String> images, Fighter fighter) {
	        super(im, x, y, images, fighter);
	    }

	@Override
	protected void doBehavior(double speed, double distance) {
		return;	
	}

	@Override
	public void update(long elapsedTime) {
		return;
	}
	
	public String toString() {
		return "platform";
	}

	@Override
	public void updateAll(long elapsedTime) {
		update(elapsedTime);
		
	}

	@Override
	public void renderAll(Graphics2D graphics) {
		render(graphics);
		
	}
}
