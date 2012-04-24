
package nicktest;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

//import platforms.fsmframework.PlatformSwitch;
import platforms.fsmframework.AbstractEvent;
import platforms.fsmframework.AbstractPlatformState;
import platforms.fsmframework.Context;
import platforms.fsmframework.PlatformSwitch;
import platforms.fsmframework.SwitchEvent;
import platforms.fsmframework.SwitchOff;
import platforms.fsmframework.SwitchOn;
import platforms.platformtypes.*;
import com.golden.gamedev.Game;



public class PlatformTest extends Game {
	
	AbstractPlatform myPlatform;
	PlatformSwitch mySwitch;
	Context myContext;
	AbstractPlatform myPlatform2;
	

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
		myPlatform2 = sp;
		List<String> imNames2 = new ArrayList<String>();
		imNames2.add("resources/Switch1.jpg"); 
		imNames2.add("resources/Switch2.jpg");
		mySwitch = new PlatformSwitch(75, 75, imNames2);
		List<AbstractPlatform> plats = new ArrayList<AbstractPlatform>();
		plats.add(sp);
		List<AbstractPlatformState> transition = new ArrayList<AbstractPlatformState>();
		transition.add(new SwitchOff(plats));
		transition.add(new SwitchOn(plats));
		
		SwitchEvent event = new SwitchEvent(mySwitch, transition, plats);
		List<AbstractEvent> events = new ArrayList<AbstractEvent>();
		events.add(event);
		myContext = new Context(events, plats);
		mySwitch.setActive(false);
		System.out.println(mySwitch.isActive());
	}

	@Override
	public void render(Graphics2D arg0) {
		arg0.setColor(Color.WHITE);
        arg0.fillRect(0, 0, getWidth(), getHeight());
        myPlatform2.render(arg0);
		myPlatform.render(arg0);
		//System.out.println(mySwitch.isActive());
		mySwitch.render(arg0);
		//System.out.println(mySwitch.isActive());
	
		
	}

	@Override
	public void update(long arg0) {
		if (keyPressed(KeyEvent.VK_S)) {
			//mySwitch.setOn(true);
		
			
		}
		myPlatform2.update(arg0);
		myPlatform.update(arg0);	
		//System.out.println(mySwitch.isActive());
		mySwitch.update(arg0);
		//System.out.println(mySwitch.isActive());
		myContext.update(arg0);
	}
	
}
    