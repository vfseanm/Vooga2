import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import platforms.*;

import sidescrolling.*;
import sprite.AnimatedGameSprite;

import attributes.*;
import collisions.GameCollisionManager;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ImageBackground;

import enemies.Enemy;
import enemies.movement.Flying;
import enemies.movement.Gravity;
import enemies.movement.JumpingMovement;
import enemies.movement.OneDirectionMovement;
import enemies.movement.SideToSideMovement;
import enemies.movement.UpDownMovement;
import fighter.Fighter;


public class TestGame extends Game {
    private Enemy bob;
    private Background myBackground;
    private int counter;
    private SimplePlatform p;
    private PlayField myPF;
    private GameCollisionManager gc;
    private ArrayList<AnimatedGameSprite> list;
    @Override
    public void initResources ()
    {
        myBackground = new ImageBackground(getImage("resources/city.jpg"));
        BufferedImage[] b = new BufferedImage[1];
        b[0]=getImage("resources/Bowser.jpg");
        ArrayList<String> a = new ArrayList<String>();
        a.add("resources/Bowser.jpg");
         bob = new Enemy(b, 300, 300, a);
        
        
       // bob.addAttribute(new Gravity(1));
        
       
        
        counter=0;
        a.clear(); 
        BufferedImage[] b1 = new BufferedImage[1];
        
        List<AnimatedGameSprite> ag = new ArrayList<AnimatedGameSprite>(); 
        b1[0]= getImage("resources/platform1.png"); 
        p = new SimplePlatform (b1, 500,500, a, null);
         list = new ArrayList<AnimatedGameSprite>();
        
        list.add(p);
         gc = new GameCollisionManager();
                
    }

    @Override
    public void render (Graphics2D arg0)
    {
        myBackground.render(arg0);
       bob.render(arg0);
       p.render(arg0);
        
    }

    @Override
    public void update (long arg0)
    {
        myBackground.update(arg0);
        counter++;
        bob.update(arg0);
//        if(counter%200==0){
//            bob.updateAttribute("JumpingMovement");
//        }
//        if(counter==400){
//            bob.updateAttribute("Flying", false);
//        }
        p.update(arg0);
        gc.GameCollision(bob, list);
        
        
        
        
        
    }

}
/*    
    private SpriteGroup group1;
    private SpriteGroup group2;
    private SpriteGroup FIGHTER_GROUP;
    private Fighter fighter;
    private Sidescroller sidescroller;
    
    
    public void setSidescroller(Sidescroller behavior) {
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
        sidescroller = new ConcreteSidescroller(group1, group2);
        sidescroller = new LeftSidescroller(this, sidescroller);
        sidescroller = new RightSidescroller(this, sidescroller);
        sidescroller = new UpSidescroller(this, sidescroller);
        sidescroller = new DownSidescroller(this, sidescroller);
        //sidescroller = new ForcedUpSidescroller(sidescroller);
        //sidescroller = new ForcedRightSidescroller(sidescroller);        
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
*/