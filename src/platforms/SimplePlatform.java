package platforms;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import fighter.Fighter;




public class SimplePlatform extends AbstractPlatform {
	
	
	private static final long serialVersionUID = 7514750773895804951L;

	public SimplePlatform(BufferedImage[] im, double x, double y, List<String> images, Fighter fighter) {
	    super(im, x, y, images, fighter);
	  
	}

	@Override
	protected void doBehavior(double speed, double distance) {
		return;	
	}
	
	@Override
	public String toString() {
		return "platform";
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}

	/*@Override
	public void renderAll(Graphics2D graphics) {
		super.render(graphics);	
	}*/
	
	@Override
	public void setVerticalSpeed(double speed) {
		super.setVerticalSpeed(speed);
	}
	
	@Override
	public void setHorizontalSpeed(double speed) {
		super.setHorizontalSpeed(speed);
	}
	
	public void render(Graphics2D graphics) {
		//super.render(graphics);
	}
	
	public void moveAll(double x, double y) {
		move(x, y);
		System.out.println("simpleplatmove");
		System.out.println(getX() + " " + getY());
	}
}
