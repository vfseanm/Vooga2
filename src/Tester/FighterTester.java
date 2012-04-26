package Tester;

import java.awt.*;


import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import platforms.platformtypes.*;

import sidescrolling.*;
import sidescrolling.border.*;
import sidescrolling.forced.*;
import sidescrolling.shift.*;
import sidescrolling.special.SidescrollerSwitch;
import sprite.AnimatedGameSprite;

import attributes.fighterattributes.FighterBasicMovement;
import attributes.fighterattributes.FighterJump;
import attributes.sharedattributes.Gravity;

import collisions.CollisionSpec;
import collisions.GameCollisionManager;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;

import fighter.*;

public class FighterTester extends Game{
	
    private ArrayList<AnimatedGameSprite> allSprites;
    private ArrayList<AnimatedGameSprite> spritess;
    private Fighter fighter;
    private Sidescroller sidescroller;
    private SpriteGroup hay;
    private CollisionSpec cs;
    private SidescrollerSwitch scrollerSwitch;
    private ArrayList<CollisionSpec> specList;
    private GameCollisionManager gc;

    public Fighter getFighter() {
        return fighter;
    }
    
    public void initFighter() {
    	String[] fightNames = {"Resources/RunningChikapu1.png", "Resources/RunningChikapu2.png", 
                "Resources/RunningChikapu3.png", "Resources/RunningChikapu4.png"};
        List<String> names = new ArrayList<String>();
        BufferedImage[] fighterIm = new BufferedImage[fightNames.length];
        for (int i = 0; i < fightNames.length; i++) {
            fighterIm[i] = getImage(fightNames[i]);
            names.add(fightNames[i]);
        }
        fighter = Fighter.getInstance();
        fighter.setX(300.0);
        fighter.setY(300.0);
        fighter.setImageNames(names);
        fighter.setImages(fighterIm);
        fighter.setUserInput(bsInput);
        
        fighter.getAnimationTimer().setDelay(300);
        fighter.setAnimationFrame(0, 3);
        fighter.setAnimate(true);
        fighter.setLoopAnim(true);
        
        fighter.addAttribute(new FighterBasicMovement(6));
        fighter.addAttribute(new FighterJump(1.5, 300));
        Gravity g = new Gravity(1.0);
        g.setActivity(true);
        fighter.addAttribute(g);
    }
    
    public void initResources() {
        
    	initFighter();
        
        allSprites = new ArrayList<AnimatedGameSprite>();
        ArrayList<String> imageName = new ArrayList<String>();
        imageName.add("Resources/block3.png");
        AbstractPlatform p1 = new SimplePlatform(400, 400, imageName);
        AbstractPlatform p2 = new SimplePlatform(200, 250, imageName);
        AbstractPlatform p3 = new SimplePlatform(500, 500, imageName);
        AbstractPlatform p4 = new SimplePlatform(10, 15, imageName);
        hay = new SpriteGroup("birds");
        hay.add(p1);
        hay.add(p2);
        hay.add(p3);
        hay.add(p4);
        allSprites.add(p1);
        allSprites.add(p2);
        allSprites.add(p3);
        allSprites.add(p4);
        
        ArrayList<String> imageName2 = new ArrayList<String>();
        String switchName = "Resources/Bowser.jpg";
        imageName2.add(switchName);
        Sidescroller newscroll = new ShiftRightSidescroller(new ShiftLeftSidescroller(new ConcreteSidescroller(allSprites)));
        //scrollerSwitch = new SidescrollerSwitch(350, 400, imageName2, newscroll, this);
        
        allSprites.add(scrollerSwitch);
        sidescroller = new ConcreteSidescroller();
        sidescroller.setSprites(allSprites);
        //sidescroller = new ForcedDownSidescroller(sidescroller);
        //sidescroller = new ForcedUpSidescroller(sidescroller);
        //sidescroller = new ForcedRightSidescroller(sidescroller);
        //sidescroller = new ForcedLeftSidescroller(sidescroller);
        //sidescroller = new ShiftRightSidescroller(sidescroller);
        //sidescroller = new ShiftLeftSidescroller(sidescroller);
        //sidescroller = new ShiftUpSidescroller(sidescroller);
        //sidescroller = new ShiftDownSidescroller(sidescroller);
        //sidescroller = new BorderToShiftLocation(sidescroller);
        sidescroller = new BorderLeftSidescroller(sidescroller);
        sidescroller = new BorderRightSidescroller(sidescroller);
        sidescroller = new BorderUpSidescroller(sidescroller);
        sidescroller = new BorderDownSidescroller(sidescroller);
        
        specList = new ArrayList<CollisionSpec>();
        cs = new CollisionSpec();
        cs.addActMap(scrollerSwitch.getGroup(), "switchSidescroller");
        cs.addActMap(fighter.getGroup(), "");
        specList.add(cs);
        
        spritess = new ArrayList<AnimatedGameSprite>();
        spritess.addAll(allSprites);
        spritess.add(fighter);
        gc = new GameCollisionManager(specList);
    }
    
    public void setSidescroller(Sidescroller newScroll) {
        sidescroller = newScroll;
    }
    
    public void render (Graphics2D pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        hay.render(pen);
        fighter.render(pen);
        scrollerSwitch.render(pen);
    }
    
    public void update(long elapsedTime) {
        hay.update(elapsedTime);
        fighter.update(elapsedTime);
        sidescroller.update(elapsedTime);
        scrollerSwitch.update(elapsedTime);
        gc.detectCollision(spritess);
    }

}