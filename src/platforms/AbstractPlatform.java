package platforms;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

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
	
	public String makeJsonString()
	{
	    Gson gson = new Gson();
	    List<String> myList = new ArrayList<String>();
	    myList.add(this.getType());
	    myList.add(gson.toJson(getImageName()));
	    myList.add(""+getX());
	    myList.add(""+getY());
	    return gson.toJson(myList);
	    
	}

}
