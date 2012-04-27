package demo;

import java.awt.Graphics2D;
import collisions.GameCollisionManager;

import sprite.AnimatedGameSprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.CollisionGroup;

import sidescrolling.*;
import sidescrolling.border.*;
import sidescrolling.forced.*;
import sidescrolling.shift.ShiftLeftSidescroller;
import sidescrolling.shift.ShiftRightSidescroller;
import sidescrolling.special.SidescrollerSwitch;
import platforms.fsmframework.AbstractEvent;
import platforms.fsmframework.AbstractPlatformState;
import platforms.fsmframework.Context;
import platforms.fsmframework.PlatformSwitch;
import platforms.fsmframework.SwitchEvent;
import platforms.fsmframework.UpDownState;
import platforms.platformtypes.*;
import collisions.CollisionSpec;

import collisions.GameCollisionManager;

import collisions.GameCollisionManager;
import enemies.Enemy;
import fighter.Fighter;

public class DemoGame extends PlatformGame {
    private SidescrollerSwitch scrollerSwitch;

	private GameCollisionManager gc, myCollisions;
	private PlatformSwitch mySwitch;
	private AbstractPlatform myPlatform;
	private Context myContext;
	public DemoGame()
		{
	    super();
	}
	public void initResources() 
	{
	  
	    loadLevel("demo1");
	    SpriteGroup allSprites = new SpriteGroup("allSprites");
	    for(AnimatedGameSprite sprite: myPlayfield.getMySprites()) {
	        allSprites.add(sprite);
	    }
	    
        myCollisions = new GameCollisionManager();
	    
        ArrayList<CollisionSpec> specList = new ArrayList<CollisionSpec>();
        CollisionSpec spec = new CollisionSpec();
        
        CollisionSpec spec2 = new CollisionSpec();
        spec2.addActMap("FIGHTER", "fighterStandOnTop");
        spec2.addActMap("FIGHTER", "fighterHitObject");
        spec2.addActMap("PLATFORM", "");
        spec2.addActMap("PLATFORM", "actionBreak");
        specList.add(spec2);
        System.out.println(myCollisions);
        
        myCollisions.setCollisionGroup(allSprites, allSprites);
        myCollisions.addSpecList(specList);


	    
	    System.out.println(myPlayfield.getMySprites());
	    
	    
	    
	    
	    
	    
//        for(AnimatedGameSprite s: mySprites)
//        {
//            System.out.print(s.getGroup() + " ");
//            System.out.println(s.getX() + "   " + s.getY());
//        }

		
       
//        //FSM stuff
//        initPlatformFSM();
//        CollisionSpec spec3 = new CollisionSpec();
//        spec3.addActMap(mySwitch.getGroup(), "switchPlatform");
//        spec3.addActMap("FIGHTER", "");
//        specList.add(spec3);
//        
//        //make sidescroller switch
//        ArrayList<String> switchImage = new ArrayList<String>();
//        String switchName = "Resources/Bowser.jpg";
//        switchImage.add(switchName);
//        Sidescroller newscroll = new ShiftRightSidescroller(new ShiftLeftSidescroller(new ConcreteSidescroller()));
//        scrollerSwitch = new SidescrollerSwitch(350, 400, switchImage, newscroll, this);
        //myPlayfield.addCollisionGroup(allSprites, allSprites, myCollisions);
        
        //mySidescroller = new ForcedRightSidescroller(new ConcreteSidescroller());
        //mySidescroller.setUserInput(bsInput);

        
        
        

	}

	private void initPlatformFSM() {
		 //FSM stuff
        List<String> imNames = new ArrayList<String>();
		imNames.add("resources/platform1.png");
		imNames.add("resources/RotatingPlatform1.png");
        SimplePlatform sp = new SimplePlatform(400, 100, imNames);
		myPlatform = sp;
		List<String> imNames2 = new ArrayList<String>();
		imNames2.add("resources/Switch1.jpg"); 
		imNames2.add("resources/Switch2.jpg");
		mySwitch = new PlatformSwitch(75, 75, imNames2);
		List<AbstractPlatform> plats = new ArrayList<AbstractPlatform>();
		plats.add(sp);
		/*List<AbstractPlatformState> transition = new ArrayList<AbstractPlatformState>();
		transition.add(new SwitchOff(plats));
		transition.add(new SwitchOn(plats));
		SwitchEvent event = new SwitchEvent(mySwitch, transition, plats);
		List<AbstractEvent> events = new ArrayList<AbstractEvent>();
		events.add(event);
		myContext = new Context(events, plats);
		*/
		
	}

	@Override
	public void render(Graphics2D arg0) 
	{

        myBackground.render(arg0);
	    myPlayfield.render(arg0);
//	    //System.out.println(getHeight());
//	    //System.out.println(getWidth());
//	    
//	    //FSM Stuff
//	    //myPlatform.render(arg0);
//	    //mySwitch.render(arg0);
	    myFighter.render(arg0);
	  
	}

	@Override
	public void update(long elapsedTime) 
	{
	   myCollisions.checkCollision(); 
	   myPlayfield.update(elapsedTime);
	   myFighter.update(elapsedTime);
	   //System.out.println(mySidescroller);
	   mySidescroller.update(elapsedTime);

	}
}
