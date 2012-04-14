package collisions;

import java.awt.Desktop.Action;
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
	HashMap<ArrayList<String>, Action> actionMap = new HashMap <ArrayList<String>, Action>();
	
	public GameCollisionManager (){
		actionList.add(new InstantDeathAction()); 
		actionList.add(new PlatformAction());
		actionList.add(new RepelBackAction()); 
	}

	public void GameCollision (ArrayList<AnimatedGameSprite> spriteList){
		for (AnimatedGameSprite gs1: spriteList){
			for(AnimatedGameSprite sprite1: spriteList){
				if (gs1==sprite1)
					continue;
				else{
					if ( CollisionChecker(sprite1, gs1) ){
						int side = SideChecker (sprite1, gs1);

						for (ActionPerformer ap: actionList)
							ap.action(sprite1, gs1, side);
					}
				}
			}
		}
	}
	
	public void addMap (ArrayList<String> name, Action act){
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
	private boolean leftRightChecker (Sprite sprite1, Sprite gs1){
		if  (
				((gs1.getX() + gs1.getWidth()==sprite1.getX())
				&& (gs1.getX()<=sprite1.getX()) && (gs1.getY()<=sprite1.getY()) 
				&& (gs1.getY()+gs1.getHeight() >= sprite1.getY()+sprite1.getHeight())) 
				|| 
				((gs1.getX() + gs1.getWidth()==sprite1.getX())
				&& (gs1.getX()<=sprite1.getX()) && (gs1.getY()>=sprite1.getY()) 
				&& (gs1.getY()+gs1.getHeight() <= sprite1.getY()+sprite1.getHeight())) 
				||
				(gs1.getX() + gs1.getWidth()==sprite1.getX())
				&& (gs1.getX()<=sprite1.getX()) && (gs1.getY()<=sprite1.getY()) 
				&& (gs1.getY()+ gs1.getHeight() >= sprite1.getY()) && (gs1.getY()+gs1.getHeight() <= sprite1.getY()+sprite1.getHeight()) 
				||
				((gs1.getX() + gs1.getWidth()==sprite1.getX())
				&& (gs1.getX()<=sprite1.getX()) && (gs1.getY()>=sprite1.getY()) 
				&& (gs1.getY()+ gs1.getHeight() <= sprite1.getY()) && (gs1.getY()+gs1.getHeight() >= sprite1.getY()+sprite1.getHeight()) ) ){
			return true;
		}
		return false;
	}
	private boolean topBottomChecker (Sprite sprite1, Sprite gs1){
		if  ((sprite1.getY() + sprite1.getHeight() == gs1.getY()) 
				&& ( (gs1.getX()-sprite1.getX()/2 <= sprite1.getX() && (gs1.getX()+gs1.getWidth()+sprite1.getX()/2 >= sprite1.getX()) ) 
						|| (sprite1.getX() >= gs1.getX() && sprite1.getX()+sprite1.getWidth() <= gs1.getX()+gs1.getWidth()) )){
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