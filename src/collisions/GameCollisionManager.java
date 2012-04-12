package collisions;

import java.util.ArrayList;
import java.util.List;
import sprite.*;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;



public class GameCollisionManager{
	List<ActionPerformer> actionList = new ArrayList<ActionPerformer>(); 
	public GameCollisionManager (){
		actionList.add(new InstantDeathAction()); 
		actionList.add(new PlatformAction());
		actionList.add(new RepelBackAction()); 
	}
	
	public void GameCollision (Sprite sprite1, ArrayList<AnimatedGameSprite> spriteList){
		for (AnimatedGameSprite gs1: spriteList){
			if (gs1.getClass()==(sprite1.getClass()))
				continue;
			else{
				if ( CollisionChecker(sprite1.getX()+sprite1.getWidth(), sprite1.getX(), gs1.getX()+gs1.getWidth(), gs1.getX()) 
						&& CollisionChecker(sprite1.getY()+sprite1.getHeight(), sprite1.getY(), gs1.getY()+gs1.getHeight(), gs1.getY()) ){
					int side = SideChecker (sprite1, gs1);
					for (ActionPerformer ap: actionList){
						ap.action(sprite1, gs1, side);
					}
				}
			}
		}

	}
	
	private int SideChecker (Sprite sprite1, AnimatedGameSprite gs1){
		if (sprite1.getX() > gs1.getX() && (sprite1.getY() < gs1.getY()+gs1.getHeight()) && (sprite1.getY() + sprite1.getHeight() > gs1.getY()) 
				&& (sprite1.getX() + sprite1.getWidth() <= gs1.getX()) ){
			//Left_to_Right collision
			return CollisionGroup.LEFT_RIGHT_COLLISION;
		}
		else if (sprite1.getX() < gs1.getX() && (sprite1.getY() < gs1.getY()+gs1.getHeight() && (sprite1.getY() + sprite1.getHeight() > gs1.getY()) )
				&& (sprite1.getX() + sprite1.getWidth() >= gs1.getX()) ){
			//Right_to_left collision
			return CollisionGroup.RIGHT_LEFT_COLLISION;
		}
		else if ((sprite1.getY() >= gs1.getY()) && (sprite1.getX() < gs1.getX()) 
				&& (sprite1.getX() + sprite1.getWidth() > gs1.getX() + gs1.getWidth()) ){
			//Top_to_Bottom
			return CollisionGroup.TOP_BOTTOM_COLLISION;
		}
		else if ( (sprite1.getY() <= gs1.getY()) && (sprite1.getX() < gs1.getX()) 
				&& (sprite1.getX() + sprite1.getWidth() > gs1.getX() + gs1.getWidth()) ){
			//Bottom_to_Top
			return CollisionGroup.BOTTOM_TOP_COLLISION;
		}
		else
			return 0; 
	}

	private boolean CollisionChecker (double csRB, double csLT, double gsRB, double gsLT){
		if ( (csLT < gsLT && csRB > gsLT) || (csLT < gsRB && csRB > gsRB) ){
			return true;
		}
		else 
			return false;
	}
}