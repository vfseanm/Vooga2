package platforms.platformtypes;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.List;

import collisions.CustomActionPerformer;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;
import fighter.Fighter;

import sprite.AnimatedGameSprite;

public abstract class AbstractPlatform extends AnimatedGameSprite {

	private static final long serialVersionUID = 1483938382856783084L;
	
	protected AbstractPlatform(double x, double y, List<String> imageSources) {
		super( x, y, imageSources);
	}
	
	protected AbstractPlatform() {}
	
	protected abstract void doBehavior(double speed, double distance);
	
	protected abstract void releaseItem();
	
	protected abstract void doBreak();
	
	public abstract Object clone();
	
	
	public void standardAction (AnimatedGameSprite sprite1, int collisionType){ 	
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			if ( (sprite1.getX() >= (this.getX() - sprite1.getWidth() ))
					&& ((sprite1.getX() + sprite1.getWidth()) <= this.getX()+ this.getWidth() + sprite1.getWidth()) ){
				sprite1.setY(this.getY()-sprite1.getHeight()-1);
				if (sprite1 instanceof Enemy){
					((Enemy)sprite1).restoreOriginalAttribute("JumpingMovement");	
				}
			}
		}
		
		if (sprite1 instanceof Enemy){
			if ((collisionType!=CollisionGroup.TOP_BOTTOM_COLLISION) && (collisionType!=CollisionGroup.BOTTOM_TOP_COLLISION))
				((Enemy)sprite1).invertAttribute("OneDirectionMovement");
			if(collisionType == CollisionGroup.BOTTOM_TOP_COLLISION){
				((Enemy)sprite1).allowAttribute("JumpingMovement", false);
			}
		}
	}
	
	public void action (AnimatedGameSprite sprite1, int collisionType, CustomActionPerformer act){
		this.standardAction (sprite1, collisionType);
		customAction (sprite1, this, collisionType, act); 
	}

}
