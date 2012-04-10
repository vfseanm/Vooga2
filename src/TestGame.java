import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import platforms.*;
import sidescrolling.*;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

import fighter.Fighter;


public class TestGame extends Game {
    
    private SpriteGroup group1;
    private SpriteGroup group2;
    private SpriteGroup FIGHTER_GROUP;
    private Fighter fighter;
    private SidescrollBehavior sidescroller;
    
    
    public void setSidescrollBehavior(SidescrollBehavior behavior) {
        this.sidescroller = behavior;
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
        System.out.println(names.get(3));
        fighter = new Fighter(this, fighterIm, 300.0, 300.0, names);
        fighter.getAnimationTimer().setDelay(300);
        fighter.setAnimationFrame(0, 3);
        fighter.setAnimate(true);
        fighter.setLoopAnim(true);
        FIGHTER_GROUP = new SpriteGroup("fight group");
        FIGHTER_GROUP.add(fighter);
        
        group1 = new SpriteGroup("1");
        group2 = new SpriteGroup("2");
        BufferedImage[] im = {getImage("Resources/block3.png")};
        ArrayList<String> imageName = new ArrayList<String>();
        imageName.add("Resources/block3.png");
        AbstractPlatform p1 = new SimplePlatform(im, 400, 400, imageName, fighter);
        AbstractPlatform p2 = new SimplePlatform(im, 200, 250, imageName, fighter);
        AbstractPlatform p3 = new SimplePlatform(im, 500, 500, imageName, fighter);
        group1.add(p1);
        group1.add(p2);
        group1.add(p3);
        AbstractPlatform p4 = new SimplePlatform(im, 10, 15, imageName, fighter);
        group2.add(p4);
        
        setSidescrollBehavior(new BasicAllDirections(this, group1, group2));
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
