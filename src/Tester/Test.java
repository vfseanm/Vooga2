package Tester;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import platforms.platformtypes.*;

import sidescrolling.*;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import com.golden.gamedev.util.ImageUtil;

import fighter.*;


public class Test extends Game{
	
	AbstractPlatform myPlatform;
	Fighter myFighter;
   
    public void initResources() {
    	BufferedImage[] ims = new BufferedImage[2];
    	ims[0] = getImage("platform1.png");
    	ims[1] = getImage("RotatingPlatform3.png");
    	ArrayList<String> imNames = new ArrayList<String>();
    	imNames.add("platform1.png");
    	imNames.add("RotatingPlatform3.png");
    	myPlatform = new SimplePlatform(ims, 200, 200, imNames);  
    	//myPlatform = new RotatingPlatform(myPlatform);
    	myPlatform = new SideToSidePlatform(myPlatform);
    	//myPlatform = new UpDownPlatform(myPlatform);
    }
    
    public void render (Graphics2D pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        myPlatform.render(pen);  
    }
    
    public void update(long elapsedTime) {
    	myPlatform.update(elapsedTime);
       
    }

}
