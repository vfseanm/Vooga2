package platforms;


import java.awt.image.BufferedImage;

import java.util.ArrayList;


import sprite.AnimatedGameSprite;
import fighter.*;

@SuppressWarnings("serial")
public abstract class AbstractPlatform extends AnimatedGameSprite {
    
    Fighter myFighter;

	protected AbstractPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter) {
		super(im, x, y, images);
		myFighter = fighter;
	}

	protected AbstractPlatform(){
	    
	}

	public abstract void doBehavior(double speed, double distance);
	public abstract void update(long elapsedTime);
	//public abstract AbstractPlatform getNextState();



}