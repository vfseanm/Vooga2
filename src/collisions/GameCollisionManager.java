package collisions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import platforms.SimplePlatform;
import sprite.*;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;



public class GameCollisionManager{
	List<ActionPerformer> actionList = new ArrayList<ActionPerformer>(); 
	HashMap<ArrayList<String>, ActionPerformer> actionMap = new HashMap <ArrayList<String>, ActionPerformer>();
	
	public GameCollisionManager (){
		actionList.add(new InstantDeathAction()); 
		actionList.add(new PlatformAction());
		actionList.add(new RepelBackAction()); 
	}

	public void GameCollision (ArrayList<AnimatedGameSprite> spriteList){
		for (AnimatedGameSprite sprite1: spriteList){
			for(AnimatedGameSprite sprite2: spriteList){
				if (sprite1==sprite2)
					continue;
				else{
					if ( CollisionChecker(sprite1, sprite2) ){
						int side = SideChecker (sprite1, sprite2);
						
						ArrayList<String> spriteName = new ArrayList<String>() ;
						spriteName.add(sprite1.getName); spriteName.add(sprite2.getName);
						ActionPerformer act = actionMap.get(spriteName);
						act.action(sprite1, sprite2, side);
					}
				}
			}
		}
	}
	
	public void addMap (ArrayList<String> name, ActionPerformer act){
		actionMap.put(name, act);
	}

	private int SideChecker(Sprite sprite1, Sprite sprite2) {
		if (leftRightChecker(sprite1, sprite2)) {
			return CollisionGroup.LEFT_RIGHT_COLLISION;
		}
		else if (leftRightChecker(sprite2, sprite1)){
			return CollisionGroup.RIGHT_LEFT_COLLISION;
		}
		else if (topBottomChecker(sprite1, sprite2)){
			return CollisionGroup.TOP_BOTTOM_COLLISION;
		}
		else if (topBottomChecker(sprite2, sprite1)){
			return CollisionGroup.BOTTOM_TOP_COLLISION;
		}
		return 0;
	}
	private boolean leftRightChecker (Sprite sprite1, Sprite sprite2){
		if  (
				((sprite2.getX() + sprite2.getWidth()==sprite1.getX())
				&& (sprite2.getX()<=sprite1.getX()) && (sprite2.getY()<=sprite1.getY()) 
				&& (sprite2.getY()+sprite2.getHeight() >= sprite1.getY()+sprite1.getHeight())) 
				|| 
				((sprite2.getX() + sprite2.getWidth()==sprite1.getX())
				&& (sprite2.getX()<=sprite1.getX()) && (sprite2.getY()>=sprite1.getY()) 
				&& (sprite2.getY()+sprite2.getHeight() <= sprite1.getY()+sprite1.getHeight())) 
				||
				(sprite2.getX() + sprite2.getWidth()==sprite1.getX())
				&& (sprite2.getX()<=sprite1.getX()) && (sprite2.getY()<=sprite1.getY()) 
				&& (sprite2.getY()+ sprite2.getHeight() >= sprite1.getY()) && (sprite2.getY()+sprite2.getHeight() <= sprite1.getY()+sprite1.getHeight()) 
				||
				((sprite2.getX() + sprite2.getWidth()==sprite1.getX())
				&& (sprite2.getX()<=sprite1.getX()) && (sprite2.getY()>=sprite1.getY()) 
				&& (sprite2.getY()+ sprite2.getHeight() <= sprite1.getY()) && (sprite2.getY()+sprite2.getHeight() >= sprite1.getY()+sprite1.getHeight()) ) ){
			return true;
		}
		return false;
	}
	private boolean topBottomChecker (Sprite sprite1, Sprite sprite2){
		if  ((sprite1.getY() + sprite1.getHeight() == sprite2.getY()) 
				&& ( (sprite2.getX()-sprite1.getX()/2 <= sprite1.getX() && (sprite2.getX()+sprite2.getWidth()+sprite1.getX()/2 >= sprite1.getX()) ) 
						|| (sprite1.getX() >= sprite2.getX() && sprite1.getX()+sprite1.getWidth() <= sprite2.getX()+sprite2.getWidth()) )){
			return true;
		}
		return false; 
	}

	private boolean CollisionChecker (Sprite sp1, Sprite sp2){
		if ( (leftRightChecker(sp1, sp2)) || ( topBottomChecker (sp1, sp2) )){
			return true;
		}
		else 
			return false;
	}
}