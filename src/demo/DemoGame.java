package demo;

import java.awt.Color;

import java.awt.Graphics2D;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

import fighter.Fighter;
import fighter.movement.BasicMovement;

import sprite.AnimatedGameSprite;

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
    import enemies.movement.JumpingMovement;
import enemies.movement.OneDirectionMovement;


    public class DemoGame extends PlatformGame {
        private Enemy bob;
        private Background myBackground;
        private int counter;
        private SimplePlatform p;
        private SimplePlatform p1, p2;
        private PlayField myPF;
        private GameCollisionManager gc;
        private ArrayList<AnimatedGameSprite> list;
        //private PlayField myPlayField;
        private SpriteGroup allSprites;
        @Override
        public void initResources ()
        {

            loadLevel("level2");
            
            Fighter myFighter = new Fighter(mySprites.get(0).getImages(), 100, 100, mySprites.get(0).getImageNames());
            System.out.println("fighter" + myFighter);
            myFighter.addAttribute(new BasicMovement(bsInput, 5));
           // myFighter.addAttribute(new Gravity(1));
            
            
            //SpriteGroup fighter = myPlayField.addGroup(new SpriteGroup("Fighter"));
            
            allSprites = new SpriteGroup("sprites");
            allSprites.add(myFighter);
            for (Sprite s: mySprites)
            {
                allSprites.add(s);
            }
           // myPlayField.addGroup(allSprites);
          
            myBackground = new ImageBackground(getImage("resources/city.jpg"));
            BufferedImage[] b = new BufferedImage[1];
            b[0]=getImage("resources/Bowser.jpg");
            ArrayList<String> a = new ArrayList<String>();
            a.add("resources/Bowser.jpg");
             bob = new Enemy(b, 500, 300, a);
            
            
            bob.addAttribute(new Gravity(1));
            bob.addAttribute(new OneDirectionMovement("left",1));        
            bob.addAttribute(new JumpingMovement(1,80));
            //bob.addAttribute(new JumpingMovement(1,100));
            
            counter=0;
            a.clear(); 
            BufferedImage[] b1 = new BufferedImage[1];
            
            List<AnimatedGameSprite> ag = new ArrayList<AnimatedGameSprite>(); 
            b1[0]= getImage("resources/platform1.png"); 
            p = new SimplePlatform (b1, 500,500, a, null);
            

            b1[0]= getImage("resources/platform1.png"); 
            p1 = new SimplePlatform (b1, 100,300, a, null);
            
            b1[0]= getImage("resources/platform1.png"); 
            p2 = new SimplePlatform (b1, 200,350, a, null);

            
             list = new ArrayList<AnimatedGameSprite>();
            
            list.add(p);
            list.add(p1);
            list.add(p2);
             gc = new GameCollisionManager();
             
             for(AnimatedGameSprite s: mySprites)
             {
                 System.out.println(s);
             }
                    
        }


        @Override
        public void render (Graphics2D arg0)
        {

            myBackground.render(arg0);
            //myPlayField.render(arg0);
            allSprites.render(arg0);
            bob.render(arg0);
           p.render(arg0);
           p1.render(arg0);
           p2.render(arg0);
           
            
        }


        @Override
        public void update (long arg0)
        {
            allSprites.update(arg0);
            myBackground.update(arg0);
            counter++;
            bob.update(arg0);
            p.update(arg0);
            p1.update(arg0);
            p2.update(arg0);
            gc.GameCollision(bob, list);
            if(counter==800){
                
            }
            
            
            
            
            
        }

    }
