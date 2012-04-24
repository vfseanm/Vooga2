import platforms.platformtypes.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import platforms.*;

import sidescrolling.*;
import sprite.AnimatedGameSprite;

import attributes.*;
import collisions.CollisionSpec;
import collisions.GameCollisionManager;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ImageBackground;

import enemies.Enemy;
import enemies.EnemyAction;
import enemies.movement.JumpingMovement;
import enemies.movement.OneDirectionMovement;
import enemies.movement.SideToSideMovement;
import enemies.movement.UpDownMovement;
import fighter.Fighter;


public class TestGame extends Game {
	private Enemy bob;
	private Background myBackground;
	private int counter;
	private AbstractPlatform p, p3, p4;
	private SimplePlatform p1, p2;
	private PlayField myPF;
	private GameCollisionManager gc;
	private ArrayList<AnimatedGameSprite> list = new ArrayList<AnimatedGameSprite>();
	private CollisionSpec cs;
	
	ArrayList<CollisionSpec> specList = new ArrayList<CollisionSpec>();

	@Override
	public void initResources ()
	{
		myBackground = new ImageBackground(getImage("resources/city.jpg"));
		BufferedImage[] b = new BufferedImage[1];
		b[0]=getImage("resources/Bowser.jpg");
		ArrayList<String> a = new ArrayList<String>();
		a.add("resources/Bowser.jpg");
		bob = new Enemy( 470, 90, a);

		bob.addAttribute(new Gravity(1));
		bob.addAttribute(new OneDirectionMovement("left",1));        
		bob.addAttribute(new JumpingMovement(1,70));


		counter=0;
		a.clear(); 
		BufferedImage[] b1 = new BufferedImage[1];
		a.add("resources/platform1.png");
		List<AnimatedGameSprite> ag = new ArrayList<AnimatedGameSprite>(); 
		b1[0]= getImage("resources/platform1.png"); 
		p = new RotatingPlatform (new SimplePlatform ( 380,160, a));
		b1[0]= getImage("resources/platform1.png"); 

		p1 = new SimplePlatform ( 140, 70, a);

		b1[0]= getImage("resources/platform1.png"); 
		p2 = new SimplePlatform ( 120, 200, a);

		b1[0]= getImage("resources/platform1.png"); 
		p3 = new SimplePlatform ( 20, 130, a);

		b1[0]= getImage("resources/platform1.png"); 
		//p4 = new RotatingPlatform (new SimplePlatform ( 390, 540, a));
		
		list.add(bob);
		list.add(p);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		//list.add(p4);
		
		Hitpoints hp = new Hitpoints(20);
		bob.addAttribute(hp);
		cs = new CollisionSpec ();
		cs.addActMap(p1.getGroup(), "");
		cs.addActMap(bob.getGroup(), "enemyLoseLife");
		cs.addActMap(bob.getGroup(), "standOnTop");
		specList.add(cs);
				
		gc = new GameCollisionManager(specList);
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
		//p4.render(arg0);
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
		p3.update(arg0);
		//p4.update(arg0);
		gc.detectCollision(list);
	}

}
