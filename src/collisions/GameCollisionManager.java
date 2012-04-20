package collisions;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import platforms.platformtypes.BreakablePlatform;
import platforms.platformtypes.SimplePlatform;

import sprite.*;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;



public class GameCollisionManager{
	HashMap<ArrayList<Class>, CustomActionPerformer> actionMap = new HashMap <ArrayList<Class>, CustomActionPerformer>();

	public void GameCollision (ArrayList<AnimatedGameSprite> spriteList){
		for (AnimatedGameSprite sprite1: spriteList){
			for(AnimatedGameSprite sprite2: spriteList){
				if (sprite1==sprite2)
					continue;
				else{
					if ( CollisionChecker(sprite1, sprite2) ){
						
						int side = SideChecker (sprite1, sprite2);
						CustomActionPerformer act = null;
						for (ArrayList<Class> c: actionMap.keySet()){
							if (c.contains(sprite1.getClass()) && c.contains(sprite2.getClass()) ){
								act = actionMap.get(c);
							}
						}	
						sprite2.action(sprite1, side, act);
					}
				}
			}
		}
	}

	public ArrayList<Class> getKey (Class spc1, Class spc2){
		ArrayList<Class> keyClass = new ArrayList<Class>();
		keyClass.add(spc1); keyClass.add(spc2);
		return keyClass;
	}
	
	public void setMap (Class spc1, Class spc2, CustomActionPerformer act){

		actionMap.put(getKey(spc1, spc2), act);
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
		if  ((sprite1.getX() + sprite1.getWidth() == sprite2.getX()) &&
				(sprite1.getY() >= (sprite2.getY()-sprite1.getHeight())) && 
				(sprite1.getX() >= (sprite2.getX()-sprite1.getWidth())) &&
				((sprite1.getY()+sprite1.getHeight()) <= (sprite2.getY()+sprite2.getHeight()+sprite1.getHeight())) ){
			return true;
		}
		return false;
	}
	private boolean topBottomChecker (Sprite sprite1, Sprite sprite2){
		if  ( ((sprite1.getY() + sprite1.getHeight() >= sprite2.getY()) && (sprite1.getY() + sprite1.getHeight() <= sprite2.getY()+sprite2.getHeight()) ) &&
				((sprite1.getX()+sprite1.getWidth()) <= (sprite2.getX()+sprite2.getWidth()+sprite1.getWidth())) ){
			return true;
		}
		return false; 
	}
	

	private boolean CollisionChecker (Sprite sp1, Sprite sp2){
		if ( (leftRightChecker(sp1, sp2)) || (leftRightChecker(sp2, sp1)) || ( topBottomChecker (sp1, sp2) || (topBottomChecker(sp2, sp1)))){
			return true;
		}
		else 
			return false;
	}
}