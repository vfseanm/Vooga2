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
import collisions.CollisionSpec;
import collisions.GameCollisionManager;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ImageBackground;

import enemies.Enemy;
import enemies.EnemyAction;
import enemies.movement.JumpingMovement;
import enemies.movement.OneDirectionMovement;

public class DemoGame extends PlatformGame {
	private Enemy bob;
	private int counter;
	private SimplePlatform p;
	private SimplePlatform p1, p2;

	private GameCollisionManager gc;
	private ArrayList<AnimatedGameSprite> list;
	// private PlayField myPlayField;
	private SpriteGroup allSprites;
	private Sidescroller sidescroller;

	@Override
	public void initResources() 
	{

	    loadLevel("level2");
        for(AnimatedGameSprite s: mySprites)
        {
            System.out.println(s.getGroup());
        }

		ArrayList<CollisionSpec> specList = new ArrayList<CollisionSpec>();
		CollisionSpec spec = new CollisionSpec ();
		spec.addActMap("enemy", "standOnTop");
		spec.addActMap("platfrom", "");
        
        specList.add(spec);        
        spec.addClass("enemy", EnemyAction.class);
        //spec.addClass("platform", AbstractPlatform.class);
        gc = new GameCollisionManager(specList);
		

	}

	@Override
	public void render(Graphics2D arg0) 
	{
		 myPlayfield.render(arg0);
	}

	@Override
	public void update(long elapsedTime) 
	{
	    
	    myPlayfield.update(elapsedTime);
	    gc.detectCollision(mySprites);
	}
}
