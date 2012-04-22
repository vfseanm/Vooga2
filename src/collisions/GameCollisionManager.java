package collisions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import sprite.*;
import com.golden.gamedev.object.collision.CollisionGroup;

public class GameCollisionManager{

	private List<CollisionSpec> specList = new ArrayList<CollisionSpec>();
	
	public GameCollisionManager(List<CollisionSpec> gs){
		specList.addAll(gs);
	}

	public void detectCollision (ArrayList<AnimatedGameSprite> spriteList){
		HashMap<AnimatedGameSprite, ArrayList<AnimatedGameSprite>> checkList = 
				new HashMap<AnimatedGameSprite, ArrayList<AnimatedGameSprite>>();
		for (AnimatedGameSprite sprite1: spriteList){
			for(AnimatedGameSprite sprite2: spriteList){

				if (sprite1==sprite2)
					continue;
				else{
					if ( CollisionChecker(sprite1, sprite2) ){						
						if (concatination (checkList, sprite1, sprite2) ){	
							int side = SideChecker (sprite1, sprite2);
							CollisionSpec cspec = traverseSpec (sprite1, sprite2); 	
							performAction (cspec, sprite1, sprite2, side);
						}
					}
				}
			}
		}
	}

	private boolean concatination(HashMap<AnimatedGameSprite, ArrayList<AnimatedGameSprite>> checkList, AnimatedGameSprite sprite1, AnimatedGameSprite sprite2){
		if ( checkList.get(sprite1) == null) {
			if (checkList.get(sprite2)==null){
				ArrayList<AnimatedGameSprite> collidedList = new ArrayList<AnimatedGameSprite>();
				collidedList.add(sprite2);
				checkList.put(sprite1, collidedList);
				return true;
			}
			else if (checkList.get(sprite2)!=null){	
				ArrayList<AnimatedGameSprite> collidedList2 = checkList.get(sprite2);
				for (AnimatedGameSprite ags: collidedList2){
					if (ags == sprite1){
						return false;
					}
					else{
						collidedList2.add(sprite1);
						checkList.put(sprite2, collidedList2);
						return true;
					}
				}
			}
		}else if (checkList.get(sprite1)!=null){
			if (checkList.get(sprite2) != null){
				ArrayList <AnimatedGameSprite> collidedList = checkList.get(sprite1);
				for (AnimatedGameSprite ags: collidedList){
					if (ags == sprite2){
						return false;
					}
					else{
						collidedList.add(sprite2);
						checkList.put(sprite1, collidedList);
						return true;
					}
				}
				ArrayList <AnimatedGameSprite> collidedList2 = checkList.get(sprite2);
				for (AnimatedGameSprite ags: collidedList2){
					if (ags == sprite1){
						return false;
					}
					else{
						collidedList2.add(sprite1);
						checkList.put(sprite2, collidedList2);
						return true;
					}
				}
				
			}else if (checkList.get(sprite2) == null){
				ArrayList<AnimatedGameSprite> collidedList = checkList.get(sprite1);
				collidedList.add(sprite2);
				checkList.put(sprite1, collidedList);
				return true;
			}
		}
		return false;
		
	}

	private int SideChecker(AnimatedGameSprite sprite1, AnimatedGameSprite sprite2) {
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
	
	private boolean leftRightChecker (AnimatedGameSprite sprite1, AnimatedGameSprite sprite2){
		if  ((sprite1.getX() + sprite1.getWidth() == sprite2.getX()) &&
				(sprite1.getX() + sprite1.getWidth() <= (sprite2.getX()+sprite1.getWidth())) && 
				(sprite1.getY() >= (sprite2.getY()-sprite1.getHeight())) &&
				((sprite1.getY()+sprite1.getHeight()) <= (sprite2.getY()+sprite2.getHeight()+sprite1.getHeight())) ){
			return true;
		}
		return false;
	}
	
	private boolean topBottomChecker (AnimatedGameSprite sprite1, AnimatedGameSprite sprite2){
		if  ( ((sprite1.getY() + sprite1.getHeight() >= sprite2.getY()) && 
				(sprite1.getY() + sprite1.getHeight() <= sprite2.getY()+sprite2.getHeight()) ) &&
				(sprite1.getX() >= (sprite2.getX()-sprite1.getWidth())) &&
				((sprite1.getX()+sprite1.getWidth()) <= (sprite2.getX()+sprite2.getWidth()+sprite1.getWidth())) ){
			return true;
		}
		return false; 
	}
	

	private boolean CollisionChecker (AnimatedGameSprite sp1, AnimatedGameSprite sp2){
		if ( (leftRightChecker(sp1, sp2)) || (leftRightChecker(sp2, sp1)) || ( topBottomChecker (sp1, sp2) || (topBottomChecker(sp2, sp1)))){
			return true;
		}
		else 
			return false;
	}

	private CollisionSpec traverseSpec (AnimatedGameSprite sprite1, AnimatedGameSprite sprite2){
		for (CollisionSpec cs: specList){
			Set<String> spriteKeys = cs.returnActMap().keySet();
			if (spriteKeys.contains(sprite1.getGroup()) && spriteKeys.contains(sprite2.getGroup())){
				return cs;
			}
		}
		return null;
	}

	private void performAction (CollisionSpec cspec, AnimatedGameSprite sp1, AnimatedGameSprite sp2, int side){
		if (cspec!=null){
			ArrayList<String> tempList1 = cspec.returnActMap().get(sp1.getGroup());
			ArrayList<String> tempList2 = cspec.returnActMap().get(sp2.getGroup());

			CollisionContext ccntext = new CollisionContext();
			ccntext.addSprite1(sp1); ccntext.addSprite2(sp2); ccntext.addSide(side);
			Object args[] = new Object[2];
			args[0] = ccntext; args[1] = cspec;

			CollisionAction caSp1 = cspec.getActionInstance(sp1.getGroup());
			CollisionAction caSp2 = cspec.getActionInstance(sp2.getGroup());

			if (caSp1!=null){
				caSp1.setSprite(sp1); 

				for (String action1: tempList1){
					if ((action1 == null ) || (action1.trim()=="")){
						continue;
					}
					try{
						Method mc = caSp1.getClass().getMethod(action1, ccntext.getClass(), cspec.getClass());
						mc.invoke(caSp1, args);
					}
					catch (Exception e){
						System.out.println ("");
					}
				}
			}
			if (caSp2 != null){
				caSp2.setSprite(sp2);

				for (String action2: tempList2){
					if ((action2 == null ) || (action2.trim()=="")){
						continue;
					}
					try{
						Method mc = caSp2.getClass().getMethod(action2, ccntext.getClass(), cspec.getClass());
						mc.invoke(caSp2, args);
					}
					catch (Exception e){
						System.out.println ("");
					}
				}

			}

		}
	}	

}

