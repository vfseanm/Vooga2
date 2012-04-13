package Tester;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import platforms.*;

import sidescrolling.*;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;

import fighter.*;


public class Test extends Game{
	
	AbstractPlatform myPlatform;
   
    public void initResources() {
    	BufferedImage[] ims = new BufferedImage[1];
    	ims[0] = getImage("platform1.png");
    	ArrayList<String> imNames = new ArrayList<String>();
    	imNames.add("platform1.png");
    	myPlatform = new SimplePlatform(ims, 200, 200, imNames, null);  
    	myPlatform = new SideToSidePlatform(myPlatform);
    	myPlatform = new UpDownPlatform(myPlatform);
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
