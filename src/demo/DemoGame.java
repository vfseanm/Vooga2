package demo;

import java.awt.Graphics2D;
import collisions.GameCollisionManager;
import sprite.AnimatedGameSprite;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import com.golden.gamedev.object.SpriteGroup;
import platforms.fsmframework.AbstractEvent;
import platforms.fsmframework.AbstractPlatformState;
import platforms.fsmframework.PlatformSwitch;
import platforms.fsmframework.SimpleEvent;
import platforms.fsmframework.SimpleState;
import platforms.fsmframework.SwitchEvent;
import platforms.fsmframework.UpDownState;
import platforms.platformtypes.*;
import collisions.CollisionSpec;

public class DemoGame extends PlatformGame {
	private GameCollisionManager myCollisions;
	private PlatformSwitch mySwitch;
	private AbstractPlatform myPlatform;
	private AbstractEvent myEvent;
	

	public DemoGame() {
	    super();
	}
	public void initResources() 
	{
	  
	    loadLevel("demo1");

	    SpriteGroup allSprites = new SpriteGroup("allSprites");
	    for(AnimatedGameSprite sprite: myPlayfield.getMySprites()) {
	        allSprites.add(sprite);
	    }
	    initPlatformFSM();
        myCollisions = new GameCollisionManager();
	    
        ArrayList<CollisionSpec> specList = new ArrayList<CollisionSpec>();
        CollisionSpec spec = new CollisionSpec();
        spec.addActMap("FIGHTER", "fighterLoseLife");
        spec.addActMap("FIGHTER", "fighterLoseHitpoints");
        spec.addActMap("FIGHTER", "instantFighterDeath");
        spec.addActMap("FIGHTER", "");
        spec.addActMap("FIGHTER", "");
        spec.addActMap("ENEMY", "instantEnemyDeath");
        spec.addActMap("ENEMY", "enemyLoseLife");
        specList.add(spec);
        
        CollisionSpec spec2 = new CollisionSpec();
        spec2.addActMap("FIGHTER", "fighterStandOnTop");
        spec2.addActMap("FIGHTER", "fighterHitObject");
        spec2.addActMap("PLATFORM", "");
        spec2.addActMap("PLATFORM", "actionBreak");
        specList.add(spec2);
        
        myCollisions.setCollisionGroup(allSprites, allSprites);
        myCollisions.addSpecList(specList);


	    
	    //System.out.println(myPlayfield.getMySprites());
	    
	    
	    
	    
	    
	    
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


        CollisionSpec spec3 = new CollisionSpec();
        spec3.addActMap("ENEMY", "enemyStandOnTop");
        spec3.addActMap("PLATFORM", "");
        specList.add(spec3);
        
        
        myCollisions.setCollisionGroup(allSprites, allSprites);
        myCollisions.addSpecList(specList);
        
	}
	
	@Override
	public void render(Graphics2D arg0) 
	{
        myBackground.render(arg0);
	    myPlayfield.render(arg0);
	    renderFSM(arg0);	
	}

	
	@Override
	public void update(long elapsedTime) 
	{		
	   updateFSM(elapsedTime);
	   myCollisions.checkCollision(); 
	   myPlayfield.update(elapsedTime);
	   myFighter.update(elapsedTime);
	   mySidescroller.update(elapsedTime);
	}
	
	private void initPlatformFSM() {
		List<String> imNames = new ArrayList<String>();
		imNames.add("resources/platform1.png");
		myPlatform = new SimplePlatform(2800, 280, imNames);
		List<String> imNames2 = new ArrayList<String>();
		imNames2.add("resources/Switch1.jpg"); 
		imNames2.add("resources/Switch2.jpg");
		mySwitch = new PlatformSwitch(2500, 660, imNames2);
		List<AbstractPlatform> plats = new ArrayList<AbstractPlatform>();
		plats.add(myPlatform);
		List<AbstractPlatformState> transition = new ArrayList<AbstractPlatformState>();
		transition.add(new SimpleState());
		transition.add(new UpDownState());
		AbstractEvent event = new SimpleEvent(transition, plats);
		event = new SwitchEvent(mySwitch, event);
		//event = new RandomEvent(event);
	    myEvent = event;
	    myEvent.setControlledPlatforms(plats);
	    myPlayfield.add(mySwitch);
	    myPlayfield.add(myPlatform);
	}

	private void renderFSM(Graphics2D graphics) {
		myPlatform.render(graphics);
		mySwitch.render(graphics);
		myFighter.render(graphics);
	}

	private void updateFSM(long elapsedTime) {
		if (keyDown(KeyEvent.VK_S)) {
			mySwitch.setOn(true);
		}
		myPlatform.update(elapsedTime);
		mySwitch.update(elapsedTime);
		myEvent.update(elapsedTime);
	}
}
