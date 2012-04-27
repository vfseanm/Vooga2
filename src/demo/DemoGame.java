package demo;

import java.awt.Graphics2D;

import attributes.Attribute;
import attributes.sharedattributes.ProjectileAttack;
import collisions.GameCollisionManager;
import sprite.AnimatedGameSprite;
import weapons.Weapon;
import weapons.enemyweapons.Fireball;

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
import enemies.Enemy;
import enemies.state.PassiveState;

public class DemoGame extends PlatformGame {
	
	
	private GameCollisionManager myCollisions;
	private PlatformSwitch mySwitch;
	private AbstractPlatform myPlatform;
	private AbstractEvent myEvent;
	private Enemy myEnemy;
	private SpriteGroup allSprites;
	

	public DemoGame() {
	    super();
	}
	public void initResources() 
	{
	  
	    loadLevel("demo1");

	    allSprites = new SpriteGroup("allSprites");
	    for(AnimatedGameSprite sprite: myPlayfield.getMySprites()) {
	        allSprites.add(sprite);
	    }
	    initPlatformFSM();
	    initEnemyFSM();
        myCollisions = new GameCollisionManager();
	    
        ArrayList<CollisionSpec> specList = new ArrayList<CollisionSpec>();
        CollisionSpec spec = new CollisionSpec();
        spec.addActMap("FIGHTER", "instantFighterDeath");
        spec.addActMap("ENEMY", "instantEnemyDeath");
        specList.add(spec);
        
        CollisionSpec enemySpec = new CollisionSpec();
        spec.addActMap("ENEMY", "enemyHitObject");
        specList.add(spec);
        
        CollisionSpec spec2 = new CollisionSpec();
        spec2.addActMap("FIGHTER", "fighterStandOnTop");
        spec2.addActMap("FIGHTER", "fighterHitObject");
        spec2.addActMap("PLATFORM", "");
        spec2.addActMap("PLATFORM", "actionBreak");
        specList.add(spec2);
        
        CollisionSpec spec3 = new CollisionSpec();
        spec3.addActMap("ENEMY", "enemyStandOnTop");
        spec3.addActMap("PLATFORM", "");
        specList.add(spec3);
        
        CollisionSpec spec4 = new CollisionSpec();
        spec4.addActMap("BONUSOBJECT", "");
        spec4.addActMap("FIGHTER", "fighterHitObject");
        spec4.addActMap("FIGHTER", "fighterGetPowerUp");
        spec4.addActMap("FIGHTER", "fighterGetCarryable");
        specList.add(spec4);
        
        CollisionSpec spec5 = new CollisionSpec();
        spec5.addActMap("BONUSOBJECT", "bonusObjectStandOnTop");
        spec5.addActMap("PLATFORM", "");
        specList.add(spec5);
        
        CollisionSpec spec6 = new CollisionSpec();
        spec6.addActMap("SWITCH", "switchPlatform" );
        spec6.addActMap("FIGHTER", "");
     
        myCollisions.setCollisionGroup(allSprites, allSprites);
        myCollisions.addSpecList(specList);
        
	}
	
	@Override
	public void render(Graphics2D arg0) 
	{	
        myBackground.render(arg0);
	    myPlayfield.render(arg0);
	    myEnemy.render(arg0);
	    renderFSM(arg0);	
	}

	
	@Override
	public void update(long elapsedTime) 
	{		
	   updateFSM(elapsedTime);
	   myEnemy.update(elapsedTime);
	   myCollisions.checkCollision(); 
	   myPlayfield.update(elapsedTime);
	   myFighter.update(elapsedTime);
	   mySidescroller.update(elapsedTime);
	}
	
	
	
	private void initEnemyFSM() {
		List<String> images = new ArrayList<String>();
		images.add("resources/Bowser.jpg");
		myEnemy = new Enemy(1000, 200, images);
		myEnemy.setState(PassiveState.getInstance());
		
		List<String> images2 = new ArrayList<String>();
		images2.add("resources/Fireball.jpg");
		AnimatedGameSprite wep = new AnimatedGameSprite(-1000, -1000, images2);
		Weapon weapon = new Fireball(wep, 0.1, 100000000, 200);
		Attribute att = new ProjectileAttack(weapon);
		myEnemy.addAttribute(att);
		allSprites.add(myEnemy);
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
	    allSprites.add(myPlatform);
	    allSprites.add(mySwitch);
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
