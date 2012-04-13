package platforms;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import characters.fighter.*;

public class SimplePlatform extends AbstractPlatform {
	
	
	private static final long serialVersionUID = 7514750773895804951L;

	public SimplePlatform(BufferedImage[] im, double x, double y, List<String> images, Fighter fighter) {
	    super(im, x, y, images, fighter);
	}

	@Override
	protected void doBehavior(double speed, double distance) {
		return;	
	}
	
	public String toString() {
		return "platform";
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}

	@Override
	public void renderAll(Graphics2D graphics) {
		render(graphics);	
	}
	
	public void setAllHorizontalSpeed(double speed) {
		setHorizontalSpeed(speed);
		//System.out.println("simpleplatmove");
	}
	
	public void moveAll(double x, double y) {
		move(x, y);
		System.out.println("simpleplatmove");
		System.out.println(getX() + " " + getY());
	}
}
