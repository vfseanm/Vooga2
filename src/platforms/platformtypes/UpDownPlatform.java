package platforms.platformtypes;

 import sprite.AnimatedGameSprite;

import collisions.CustomActionPerformer;

import com.golden.gamedev.object.collision.CollisionGroup;


 /**
 * Class used to decorate platforms in order to add up and down movement i.e.
 * this class will allow the user to add functionality to a platform to allow it
 * to traverse a vertical distance repeatedly.
 * 
 * @author yankeenjg
 * 
 */
 public class UpDownPlatform extends DecoratedPlatform {

	private static final long serialVersionUID = -3578102991430723896L;

	/**
	 * Constructor for an UpDownPlatform
	 * 
	 * @param decoratorComponent
	 *            AbstractPlatform representing the platform that this class
	 *            decorates
	 */
	public UpDownPlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
		mySpeed = Double.parseDouble(myPlatformResources
				.getString("SideToSideSpeed"));
		myDistance = Double.parseDouble(myPlatformResources
				.getString("SideToSideDistance"));
	}

	/**
	 * Function to implement the behavior of this object and which is called in
	 * the update function defined in its super class. In this case, moving the
	 * platform vertically back and forth across a specific distance
	 * 
	 * @param speed
	 *            double defines the movement speed of the platform
	 * @param distance
	 *            double defines the movement range of the platform
	 */
	@Override
	public void doBehavior(double speed, double distance) {
		double time = (distance * myDistanceOffset) / speed;
		if (myTimer.getPassedFrames() % (time * 2) == 0) {
			setVerticalSpeed(speed / mySpeedOffset);
		} else if (myTimer.getPassedFrames() % time == 0) {
			setVerticalSpeed(-speed / mySpeedOffset);
		}
		myTimer.update();
	}

	/**
	 * Used in saving platforms in the level editor this method creates a string
	 * representing this platform class as well as the platforms that it may
	 * decorate.
	 */
	@Override
	public String toString() {
		return myPlatformResources.getString("UpDown")
				+ myDecoratorComponent.toString();
	}
 	
    public Object clone()
    {
        AbstractPlatform toWrap = null;
        if(myDecoratorComponent!=null)
        {
            toWrap = (AbstractPlatform) myDecoratorComponent.clone();
            
        }
        return new UpDownPlatform(toWrap);
        
    }
    
	public void action (AnimatedGameSprite sprite1, int collisionType, CustomActionPerformer act){
		standardAction (sprite1, collisionType);
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			sprite1.setY(this.getY()-sprite1.getHeight()-1);
		}
		
		customAction (sprite1, this, collisionType, act); 
	}
	
	   public static AbstractPlatform fromJson(String json)
	    {
	        return AbstractPlatform.fromJson(json);
	    }

}
