package collisions;

import platforms.AbstractPlatform;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;
import fighter.Fighter;

public class RepelBackAction extends ActionPerformer{
	
	public void action (Fighter sprite1, Enemy sprite2, int collisionType){
		if (collisionType == CollisionGroup.RIGHT_LEFT_COLLISION){
			for (int i=0; i<=40; i++){
				sprite1.setX(sprite1.getX()-1);
			}
		}
		else if (collisionType == CollisionGroup.LEFT_RIGHT_COLLISION){
			for (int i=0; i<=40; i++){
				 sprite1.setX(sprite1.getX() + 1);
			}
		}
		else if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			for (int i=0; i<=40; i++){
				 sprite1.setY(sprite1.getY()+1);
			}
		}
		else if (collisionType== CollisionGroup.BOTTOM_TOP_COLLISION){
			for (int i=0; i<=40; i++){
				sprite1.setY(sprite1.getY()-1);
			}
		}
	}
	
	public void action (Fighter sprite1, Fighter sprite2, int collisionType){
		if (collisionType== CollisionGroup.RIGHT_LEFT_COLLISION ){
			for (int i=0; i<=40; i++){
				sprite1.setX(sprite1.getX()-1);
			}
		}
	}
	
	public void action (Fighter sprite1, AbstractPlatform sprite2, int collisionType){}
	

	@Override
	public void action(Sprite sprite1, Sprite sprite2, int collisionType) {
		// TODO Auto-generated method stub
		
	}
}
