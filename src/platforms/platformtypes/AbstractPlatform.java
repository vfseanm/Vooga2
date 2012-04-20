package platforms.platformtypes;
import java.util.List;

import collisions.CustomActionPerformer;

import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;


import java.util.ResourceBundle;
import sprite.AnimatedGameSprite;

/**
 * This class provides abstract functionality for platform classes. It is the
 * super class for every type of platform.
 * 
 * @author yankeenjg
 * 
 */
public abstract class AbstractPlatform extends AnimatedGameSprite {

	private static final long serialVersionUID = 1483938382856783084L;
	protected ResourceBundle myPlatformResources = ResourceBundle
			.getBundle("platforms.PlatformResourceBundle");

	/**
	 * Super constructor used for a simple platform
	 * 
	 * @param x
	 *            double that represents the x position of the platform in the
	 *            game
	 * @param y
	 *            double represents the y position of the platform in the game
	 * @param imageSources
	 *            list of filenames used to retrieve image files that will
	 *            represent the platform in the game
	 */

	protected AbstractPlatform(double x, double y, List<String> imageSources) {
		super(x, y, imageSources);
	}

	/**
	 * Super constructor used for decorated platforms
	 */
	protected AbstractPlatform() {
	}

	/**
	 * Function that implements the behavior of each type of platform
	 * 
	 * @param speed
	 *            double that defines the movement speed of moving platforms
	 * @param distance
	 *            double that defines the distance that a moving platform should
	 *            move back and forth across
	 */
	protected abstract void doBehavior(double speed, double distance);
	
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
