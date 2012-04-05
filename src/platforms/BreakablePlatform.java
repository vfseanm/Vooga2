package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import powerUps.PowerUp;
import sprite.GameSprite;

@SuppressWarnings("serial")
public class BreakablePlatform extends AbstractPlatform {
	
	ArrayList<BreakablePlatformItemFactory> myFactoryList = new ArrayList<BreakablePlatformItemFactory>();

	protected BreakablePlatform(BufferedImage[] im, double x, double y, ArrayList<String> images) {
		super(im, x, y, images);
	}
	
	//TODO: implement getFactory methods when Dustin creates items
	public void addItem(PowerUp item) {
		myFactoryList.add(item.getFactory());
	}
	
	//TODO: implement makeItem methods when Dustin creates items
	public void releaseItem() {
		Random chooser = new Random();
		BreakablePlatformItemFactory factory = myFactoryList.get(chooser.nextInt(myFactoryList.size()));
		factory.getItem().makeItem(getX(), getY());
	}
	
	//TODO: refused bequest on parameters...will fix later...
	public void doBehavior(double speed, double distance) {
		
	}
}
