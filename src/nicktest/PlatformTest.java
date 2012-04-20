
package nicktest;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

//import platforms.fsmframework.PlatformSwitch;
import platforms.fsmframework.PlatformSwitch;
import platforms.platformtypes.*;
import playfield.SingletinPlayfield;
import com.golden.gamedev.Game;



public class PlatformTest extends Game {
	
	AbstractPlatform myPlatform;
	PlatformSwitch mySwitch;
	int counter;

	@Override
	public void initResources() {
		BufferedImage[] im = new BufferedImage[2];
		im[0] = getImage("resources/platform1.png");
		im[1] = getImage("resources/RotatingPlatform1.png");
		List<String> imNames = new ArrayList<String>();
		imNames.add("resources/platform1.png");
		imNames.add("resources/RotatingPlatform1.png");
		myPlatform = new SimplePlatform(200, 200,imNames);
		myPlatform = new RotatingPlatform(myPlatform);	
		myPlatform = new SideToSidePlatform(myPlatform);
		myPlatform = new UpDownPlatform(myPlatform);
		System.out.println(myPlatform.toString());
		SimplePlatform sp = new SimplePlatform(400, 100, imNames);
		List<String> imNames2 = new ArrayList<String>();
		imNames2.add("resources/Switch1.jpg"); 
		imNames2.add("resources/Switch2.jpg");
		
		mySwitch = new PlatformSwitch(sp, 75, 75, imNames2);
	}

	@Override
	public void render(Graphics2D arg0) {
		arg0.setColor(Color.WHITE);
        arg0.fillRect(0, 0, getWidth(), getHeight());
		myPlatform.render(arg0);
		mySwitch.render(arg0);
		if(counter>10) SingletinPlayfield.getInstance();
	
		
	}

	@Override
	public void update(long arg0) {
		if (keyPressed(KeyEvent.VK_S)) {
			mySwitch.pushed();
			counter++;
			
		}
		myPlatform.update(arg0);	
		mySwitch.update(arg0);
	}
	
}
    