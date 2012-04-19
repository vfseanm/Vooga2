
package nicktest;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import platforms.platformtypes.*;
import com.golden.gamedev.Game;



public class PlatformTest extends Game {
	
	AbstractPlatform myPlatform;

	@Override
	public void initResources() {
		BufferedImage[] im = new BufferedImage[2];
		im[0] = getImage("resources/platform1.png");
		im[1] = getImage("resources/RotatingPlatform1.png");
		List<String> imNames = new ArrayList<String>();
		imNames.add("resources/platform1.png");
		imNames.add("resources/RotatingPlatform1.png");
		myPlatform = new SimplePlatform(im, 200, 200,imNames);
		myPlatform = new RotatingPlatform(myPlatform);	
	}

	@Override
	public void render(Graphics2D arg0) {
		arg0.setColor(Color.WHITE);
        arg0.fillRect(0, 0, getWidth(), getHeight());
		myPlatform.render(arg0);
	
		
	}

	@Override
	public void update(long arg0) {
		myPlatform.update(arg0);	
	}
	
}
    