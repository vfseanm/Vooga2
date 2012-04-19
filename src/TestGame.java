import platforms.platformtypes.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import platforms.*;

import sidescrolling.*;
import sprite.AnimatedGameSprite;

import attributes.*;
import collisions.GameCollisionManager;
import collisions.PlatformAction;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ImageBackground;

import enemies.Enemy;
import enemies.movement.JumpingMovement;
import enemies.movement.OneDirectionMovement;
import enemies.movement.SideToSideMovement;
import enemies.movement.UpDownMovement;
import fighter.Fighter;


public class TestGame extends Game {
	private Enemy bob;
	private Background myBackground;
	private int counter;
	private AbstractPlatform p, p3;
	private SimplePlatform p1, p2;
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
		bob = new Enemy(b, 380, 160, a);



		bob.addAttribute(new Gravity(1));
		bob.addAttribute(new OneDirectionMovement("left",1));        
		bob.addAttribute(new JumpingMovement(1,70));


		counter=0;
		a.clear(); 
		BufferedImage[] b1 = new BufferedImage[1];

		List<AnimatedGameSprite> ag = new ArrayList<AnimatedGameSprite>(); 
		b1[0]= getImage("resources/platform1.png"); 
		p = new RotatingPlatform (new SimplePlatform (b1, 290,170, a));
		//p = new BreakablePlatform (new SimplePlatform (b1, 380, 240, a));



		b1[0]= getImage("resources/platform1.png"); 
		p1 = new SimplePlatform (b1, 160, 200, a);

		b1[0]= getImage("resources/platform1.png"); 
		p2 = new SimplePlatform (b1, 5, 130, a);
		
		b1[0]= getImage("resources/platform1.png"); 
		//p3 = new SimplePlatform (b1, 360,220, a);
		p3 = new BreakablePlatform (new SimplePlatform (b1, 120, 30, a));


		list = new ArrayList<AnimatedGameSprite>();
		p1.setGroup("PLATFORM");
		bob.setGroup("ENEMY");
		p.setGroup("PLATFORM");
		
		p2.setGroup("PLATFORM");
		p3.setGroup("PLATFORM");

		list.add(bob);
		list.add(p);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		gc = new GameCollisionManager();
		gc.setMap("ENEMY", "PLATFORM", new PlatformAction());
		//System.out.println(p.getY());

	}

	@Override
	public void render (Graphics2D arg0)
	{
		myBackground.render(arg0);
		bob.render(arg0);
		p.render(arg0);
		p1.render(arg0);
		p2.render(arg0);
		p3.render(arg0);

	}

	@Override
	public void update (long arg0)
	{
		myBackground.update(arg0);
		counter++;
		bob.update(arg0);
		p.update(arg0);
		p1.update(arg0);
		p2.update(arg0);
		gc.GameCollision(list);
	}

}
