package collisions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sprite.*;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;



public class GameCollisionManager{
	HashMap<String, ActionPerformer> actionMap = new HashMap <String, ActionPerformer>();

	public void GameCollision (ArrayList<AnimatedGameSprite> spriteList){
		for (AnimatedGameSprite sprite1: spriteList){
			for(AnimatedGameSprite sprite2: spriteList){
				if (sprite1==sprite2)
					continue;
				else{
					if ( CollisionChecker(sprite1, sprite2) ){
						int side = SideChecker (sprite1, sprite2);
						
						String combineName = stringConcatination (sprite1.getGroup(), sprite2.getGroup());
						ActionPerformer act = actionMap.get(combineName);
						
						try{
							Class <? extends Sprite> sp1c = sprite1.getClass();
							Class <? extends Sprite> sp2c = sprite2.getClass();

							Method mc = act.getClass().getMethod("action", sp1c, sp2c, Integer.class);
							Object [] args = new Object[3];
							args[0] = sprite1;
							args[1] = sprite2;
							args[2] = (Integer)side;
							mc.invoke(act, args);
						}
						catch (Exception e){
							System.out.println ("Error");
						}
					}
				}
			}
		}
	}
	private String stringConcatination(String name1, String name2){
		String combineName = null;
		if (name1.compareToIgnoreCase(name2) == -1)
			combineName = name2 + name1;
		else
			combineName = name1 + name2;
		return combineName;
	}
	
	public void setMap (String name1, String name2, ActionPerformer act){
		actionMap.put(stringConcatination(name1, name2), act);
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