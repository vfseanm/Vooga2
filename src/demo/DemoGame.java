package demo;

import java.awt.Color;



import java.awt.Graphics2D;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

import fighter.Fighter;
import fighter.movement.BasicMovement;
import fighter.movement.Jump;

import sprite.AnimatedGameSprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import platforms.*;
import sidescrolling.*;
import sidescrolling.border.*;
import platforms.platformtypes.*;
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
	private int counter;
	private SimplePlatform p;
	private SimplePlatform p1, p2;
	private PlayField myPF;
	private GameCollisionManager gc;
	private ArrayList<AnimatedGameSprite> list;
	// private PlayField myPlayField;
	private SpriteGroup allSprites;
	private Sidescroller sidescroller;

	@Override
	public void initResources() {

		// gc.setMap("koopa", "mushroom", RepelBackAction.class);
		// gc.setMap("ENEMY", "ENEMY", act);

		loadLevel("level2");
		BufferedImage[] images = new BufferedImage[4];
		images = mySprites.get(0).getImages();
		ArrayList<Attribute> toGive = new ArrayList<Attribute>();
		/*
		 * InvincibilityPowerUp powerup = new InvincibilityPowerUp(images, 0, 0,
		 * mySprites.get(0).getImageNames(), toGive, toGive);
		 */
		
		Fighter myFighter = new Fighter(100, 100, mySprites.get(0).getImageNames(), bsInput);
		myFighter.addAttribute(new BasicMovement(bsInput, 5));
		// myFighter.addAttribute(new Gravity(1));
		myFighter.addAttribute(new Jump(bsInput, 1.5, 3));

		// SpriteGroup fighter = myPlayField.addGroup(new
		// SpriteGroup("Fighter"));

		allSprites = new SpriteGroup("sprites");
		//allSprites.add(myFighter);
		// allSprites.add(powerup);

		for (Sprite s : mySprites) {
			allSprites.add(s);
		}
		// myPlayField.addGroup(allSprites);

		myBackground = new ImageBackground(getImage("resources/city.jpg"));
		BufferedImage[] b = new BufferedImage[1];
		b[0] = getImage("resources/Bowser.jpg");
		ArrayList<String> a = new ArrayList<String>();
		a.add("resources/Bowser.jpg");
		bob = new Enemy( 500, 300, a);

		bob.addAttribute(new Gravity(1));
		bob.addAttribute(new OneDirectionMovement("left", 1));
		bob.addAttribute(new JumpingMovement(1, 80));
		// bob.addAttribute(new JumpingMovement(1,100));

		counter = 0;
		a.clear();
		BufferedImage[] b1 = new BufferedImage[1];
		a.add("resources/platform1.png");
		List<AnimatedGameSprite> ag = new ArrayList<AnimatedGameSprite>();
		b1[0] = getImage("resources/platform1.png");
		p = new SimplePlatform(500, 500, a);

		b1[0] = getImage("resources/platform1.png");
		p1 = new SimplePlatform( 100, 300, a);

		b1[0] = getImage("resources/platform1.png");
		p2 = new SimplePlatform( 200, 350, a);

		list = new ArrayList<AnimatedGameSprite>();

		list.add(p);
		list.add(p1);
		list.add(p2);
		list.add(bob);
        sidescroller = new ConcreteSidescroller(myFighter, allSprites);
        sidescroller = new BorderLeftSidescroller(this, sidescroller, 100);
        sidescroller = new BorderRightSidescroller(this, sidescroller, 100);
        sidescroller = new BorderUpSidescroller(this, sidescroller, 1.5, 100);
        sidescroller = new BorderDownSidescroller(this, sidescroller, -1.0, 100);
		gc = new GameCollisionManager();

	}

	@Override
	public void render(Graphics2D arg0) {

		myBackground.render(arg0);
		// myPlayField.render(arg0);
		// allSprites.render(arg0);

		for (Sprite s : allSprites.getSprites()) {
			if (s != null) {

				s.render(arg0);
			}
		}
		bob.render(arg0);
		p.render(arg0);
		p1.render(arg0);
		p2.render(arg0);
	}

	@Override
	public void update(long arg0) {
		allSprites.update(arg0);
		myBackground.update(arg0);
		counter++;
		bob.update(arg0);
		p.update(arg0);
		p1.update(arg0);
		p2.update(arg0);
		gc.GameCollision(list);
		if (counter == 800) {

		}
	}
}
