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
import fighter.movement.BasicMovement;


public class Test extends Game{
	
    private SpriteGroup group1;
    private SpriteGroup group2;
    private SpriteGroup FIGHTER_GROUP;
    private Fighter fighter;
    private Sidescroller sidescroller;

    public Fighter getFighter() {
        return fighter;
    }
    
    public void initResources() {
        String[] fightNames = {"Resources/RunningChikapu1.png", "Resources/RunningChikapu2.png", 
                "Resources/RunningChikapu3.png", "Resources/RunningChikapu4.png"};
        List<String> names = new ArrayList<String>();
        BufferedImage[] fighterIm = new BufferedImage[fightNames.length];
        for (int i = 0; i < fightNames.length; i++) {
            fighterIm[i] = getImage(fightNames[i]);
            names.add(fightNames[i]);
        }
        fighter = new Fighter(fighterIm, 300.0, 300.0, names);
        fighter.getAnimationTimer().setDelay(300);
        fighter.setAnimationFrame(0, 3);
        fighter.setAnimate(true);
        fighter.setLoopAnim(true);
        fighter.addAttribute(new BasicMovement(bsInput, 1.5));
        FIGHTER_GROUP = new SpriteGroup("fight group");
        FIGHTER_GROUP.add(fighter);
        
        group1 = new SpriteGroup("1");
        group2 = new SpriteGroup("2");
        BufferedImage[] im = {getImage("Resources/block3.png")};
        ArrayList<String> imageName = new ArrayList<String>();
        imageName.add("Resources/block3.png");
        AbstractPlatform p1 = new SimplePlatform(im, 400, 400, imageName);
        AbstractPlatform p2 = new SimplePlatform(im, 200, 250, imageName);
        AbstractPlatform p3 = new SimplePlatform(im, 500, 500, imageName);
        group1.add(p1);
        group1.add(p2);
        group1.add(p3);
        AbstractPlatform p4 = new SimplePlatform(im, 10, 15, imageName);
        group2.add(p4);
        sidescroller = new ConcreteSidescroller(fighter, group1, group2);
        //sidescroller = new LeftSidescroller(this, sidescroller, 1.0, 100);
        //sidescroller = new RightSidescroller(this, sidescroller, -1.5, 100);
        //sidescroller = new UpSidescroller(this, sidescroller, 1.0, 0);
        //sidescroller = new DownSidescroller(this, sidescroller, -1.0, 0);
        //sidescroller = new ForcedDownSidescroller(sidescroller, -1.0);
        //sidescroller = new ForcedRightSidescroller(sidescroller, -1.0);
        sidescroller = new SkipRightSidescroller(this, sidescroller);
        sidescroller = new SkipLeftSidescroller(this, sidescroller);
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
