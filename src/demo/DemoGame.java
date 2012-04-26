package demo;

import java.awt.Color;



import java.awt.Graphics2D;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

import fighter.Fighter;

import sprite.AnimatedGameSprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import platforms.*;
import sidescrolling.*;
import sidescrolling.border.*;
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
import attributes.*;
import attributes.fighterattributes.FighterBasicMovement;
import attributes.fighterattributes.FighterJump;
import collisions.CollisionSpec;
import collisions.GameCollisionManager;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ImageBackground;

import enemies.Enemy;
import enemies.EnemyAction;
import enemies.movement.JumpingMovement;
import enemies.movement.OneDirectionMovement;

public class DemoGame extends PlatformGame {
	private Enemy bob;
	private int counter;
	private SimplePlatform p;
	private SimplePlatform p1, p2;
	
	private SidescrollerSwitch scrollerSwitch;

	private GameCollisionManager gc;
	private ArrayList<AnimatedGameSprite> list;
	// private PlayField myPlayField;
	private SpriteGroup allSprites;
	
	private PlatformSwitch mySwitch;
	private AbstractPlatform myPlatform;
	private Context myContext;

	@Override
	public void initResources() 
	{

	    loadLevel("level2");
        for(AnimatedGameSprite s: mySprites)
        {
            System.out.println(s.getGroup());
        }

		ArrayList<CollisionSpec> specList = new ArrayList<CollisionSpec>();
		CollisionSpec spec = new CollisionSpec ();
		spec.addActMap("enemy", "standOnTop");
		spec.addActMap("platform", "");
		
        
        specList.add(spec);        
        //spec.addClass("enemy", EnemyAction.class);
        //spec.addClass("platform", AbstractPlatform.class);
       
        //FSM stuff
        initPlatformFSM();
        CollisionSpec spec2 = new CollisionSpec();
        spec2.addActMap(mySwitch.getGroup(), "switchPlatform");
        spec2.addActMap("Fighter", "");
        specList.add(spec2);
        
        //make sidescroller switch
        ArrayList<String> switchImage = new ArrayList<String>();
        String switchName = "Resources/Bowser.jpg";
        switchImage.add(switchName);
        Sidescroller newscroll = new ShiftRightSidescroller(new ShiftLeftSidescroller(new ConcreteSidescroller(mySprites)));
        scrollerSwitch = new SidescrollerSwitch(350, 400, switchImage, newscroll, this);
        
        //Special sidescroller-switch collision stuff
        specList = new ArrayList<CollisionSpec>();
        CollisionSpec spec3 = new CollisionSpec();
        spec3.addActMap(scrollerSwitch.getGroup(), "switchSidescroller");
        spec3.addActMap(getFighter().getGroup(), "");
        specList.add(spec3);
        
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
	
	public void setSidescroller(Sidescroller newScroll) {
        mySidescroller = newScroll;
    }

	@Override
	public void render(Graphics2D arg0) 
	{
	    myPlayfield.render(arg0);
	    
	    //FSM Stuff
	    myPlatform.render(arg0);
	    mySwitch.render(arg0);
	}

	@Override
	public void update(long elapsedTime) 
	{
	    
	    myPlayfield.update(elapsedTime);
	    gc.detectCollision(mySprites);
	    mySidescroller.update(elapsedTime);
	    
	    //FSM stuff
	    if (keyPressed(KeyEvent.VK_S)) {
			mySwitch.setOn(true);		
		}
	    myPlatform.update(elapsedTime);
	    myContext.update(elapsedTime);
	    mySwitch.update(elapsedTime);
	}
}
