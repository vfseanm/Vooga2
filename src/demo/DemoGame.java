package demo;

import java.awt.Graphics2D;

import sprite.AnimatedGameSprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import sidescrolling.*;
import sidescrolling.shift.ShiftLeftSidescroller;
import sidescrolling.shift.ShiftRightSidescroller;
import sidescrolling.special.SidescrollerSwitch;
import platforms.fsmframework.AbstractEvent;
import platforms.fsmframework.AbstractPlatformState;
import platforms.fsmframework.Context;
import platforms.fsmframework.PlatformSwitch;
import platforms.fsmframework.SwitchEvent;
import platforms.fsmframework.SwitchOff;
import platforms.fsmframework.SwitchOn;
import platforms.platformtypes.*;
import collisions.CollisionSpec;
import collisions.GameCollisionManager;
import fighter.Fighter;

public class DemoGame extends PlatformGame {
    private SidescrollerSwitch scrollerSwitch;

	private GameCollisionManager gc;
	private PlatformSwitch mySwitch;
	private AbstractPlatform myPlatform;
	private Context myContext;
	private Fighter myFighter;

	public DemoGame()
	{
	    super();
	}
	@Override
	public void initResources() 
	{
	    loadLevel("level2");
//        for(AnimatedGameSprite s: mySprites)
//        {
//            System.out.print(s.getGroup() + " ");
//            System.out.println(s.getX() + "   " + s.getY());
//        }
		ArrayList<CollisionSpec> specList = new ArrayList<CollisionSpec>();
		CollisionSpec spec = new CollisionSpec();
		spec.addActMap("ENEMY", "standOnTop");
		spec.addActMap("PLATFORM", "");
		specList.add(spec);
		
		CollisionSpec spec2 = new CollisionSpec();
		spec2.addActMap("FIGHTER", "fighterStandOnTop");
		spec2.addActMap("PLATFORM", "");
		specList.add(spec2);
       
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
        
        //Special sidescroller-switch collision stuff
//        specList = new ArrayList<CollisionSpec>();
//        CollisionSpec spec4 = new CollisionSpec();
//        spec4.addActMap(scrollerSwitch.getGroup(), "switchSidescroller");
//        spec4.addActMap(getFighter().getGroup(), "");
//        specList.add(spec4);
        
        gc = new GameCollisionManager(specList);
        

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
		List<AbstractPlatformState> transition = new ArrayList<AbstractPlatformState>();
		transition.add(new SwitchOff(plats));
		transition.add(new SwitchOn(plats));
		SwitchEvent event = new SwitchEvent(mySwitch, transition, plats);
		List<AbstractEvent> events = new ArrayList<AbstractEvent>();
		events.add(event);
		myContext = new Context(events, plats);
		
	}

	@Override
	public void render(Graphics2D arg0) 
	{
	    myPlayfield.render(arg0);
	    
	    //FSM Stuff
	    //myPlatform.render(arg0);
	    //mySwitch.render(arg0);
	}

	@Override
	public void update(long elapsedTime) 
	{
	    
	    myPlayfield.update(elapsedTime);
	    gc.detectCollision(myPlayfield.getMySprites());
	    mySidescroller.update(elapsedTime);
	    
	    //FSM stuff
	    if (keyPressed(KeyEvent.VK_S)) {
			mySwitch.setOn(true);		
		}
	    //myPlatform.update(elapsedTime);
	    //myContext.update(elapsedTime);
	    //mySwitch.update(elapsedTime);
	}
}
