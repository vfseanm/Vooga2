package Tester;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import platforms.platformtypes.*;

import sidescrolling.*;
import sidescrolling.border.*;

import attributes.Gravity;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;

import fighter.*;
import fighter.movement.*;


public class FighterTester extends Game{
	
    private SpriteGroup group1;
    private SpriteGroup group2;
    private SpriteGroup FIGHTER_GROUP;
    private Fighter fighter;
    private Sidescroller sidescroller;

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
        fighter = new Fighter(300.0, 300.0, names);
        fighter.setUserInput(bsInput);
        
        fighter.getAnimationTimer().setDelay(300);
        fighter.setAnimationFrame(0, 3);
        fighter.setAnimate(true);
        fighter.setLoopAnim(true);
        fighter.addAttribute(new BasicMovement(6));
        fighter.addAttribute(new Jump(1.5, 300));
        Gravity g = new Gravity(1.0);
        g.setActivity(true);
        fighter.addAttribute(g);
        FIGHTER_GROUP = new SpriteGroup("fight group");
        FIGHTER_GROUP.add(fighter);
    }
    
    public void initResources() {
        
    	initFighter();
        
        group1 = new SpriteGroup("1");
        group2 = new SpriteGroup("2");
        ArrayList<String> imageName = new ArrayList<String>();
        imageName.add("Resources/block3.png");
        AbstractPlatform p1 = new SimplePlatform(400, 400, imageName);
        AbstractPlatform p2 = new SimplePlatform(200, 250, imageName);
        AbstractPlatform p3 = new SimplePlatform(500, 500, imageName);
        group1.add(p1);
        group1.add(p2);
        group1.add(p3);
        AbstractPlatform p4 = new SimplePlatform(10, 15, imageName);
        group2.add(p4);
        sidescroller = new ConcreteSidescroller(this, fighter, group1, group2);
        sidescroller = new BorderLeftSidescroller(sidescroller, 100);
        sidescroller = new BorderRightSidescroller(sidescroller, 100);
        sidescroller = new BorderUpSidescroller(sidescroller, 100);
        sidescroller = new BorderDownSidescroller(sidescroller, 100);
        //sidescroller = new ForcedDownSidescroller(sidescroller, 0.2);
        //sidescroller = new ForcedUpSidescroller(sidescroller, -1.0);
        //sidescroller = new ForcedRightSidescroller(sidescroller, 1.0);
        //sidescroller = new ForcedLeftSidescroller(sidescroller, -1.0);
        //sidescroller = new ShiftRightSidescroller(sidescroller);
        //sidescroller = new ShiftLeftSidescroller(sidescroller);
    }
    
    public void render (Graphics2D pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        group1.render(pen);
        group2.render(pen);
        fighter.render(pen);
        FIGHTER_GROUP.render(pen);
    }
    
    public void update(long elapsedTime) {
        group1.update(elapsedTime);
        group2.update(elapsedTime);
        FIGHTER_GROUP.update(elapsedTime);
        sidescroller.update(elapsedTime);
    }

}