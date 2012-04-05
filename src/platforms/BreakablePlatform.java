package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import powerUps.PowerUp;

@SuppressWarnings("serial")
public class BreakablePlatform extends AbstractPlatform {
	
	ArrayList<BreakablePlatformItemFactory> myFactoryList = new ArrayList<BreakablePlatformItemFactory>();

	protected BreakablePlatform(BufferedImage[] im, double x, double y, ArrayList<String> images) {
		super(im, x, y, images);
	}
	
	//TODO: implement getFactory methods when Dustin creates items
	public void addItem(BreakablePlatformItemFactory factory) {
		myFactoryList.add(factory);
	}
	
	//TODO: implement makeItem methods when Dustin creates items
	public PowerUp releaseItem() {
		Random chooser = new Random();
		BreakablePlatformItemFactory factory = myFactoryList.get(chooser.nextInt(myFactoryList.size()));
		return factory.getItem().makeItem(getX(), getY());
	}
	
	//TODO: refused bequest on parameters...will fix later...
	public void doBehavior(double speed, double distance) {
		
	}
}
