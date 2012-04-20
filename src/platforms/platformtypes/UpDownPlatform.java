package platforms.platformtypes;

import collisions.CustomActionPerformer;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;


public class UpDownPlatform extends DecoratedPlatform {

	private static final long serialVersionUID = -3578102991430723896L;

	public UpDownPlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
	}
	
	public void doBehavior(double speed, double distance) {
		double time = (distance * 5) / speed;
		if (myTimer.getPassedFrames() % (time * 2) == 0) {
			setVerticalSpeed(speed / 25);
		}
		else if (myTimer.getPassedFrames() % time == 0) {
			setVerticalSpeed(-speed/25);
		}
		myTimer.update();
	}
	
	public String toString() {
		return "up and down" + myDecoratorComponent.toString();
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
    
	public void action (Sprite sprite1, int collisionType, CustomActionPerformer act){
		standardAction (sprite1, collisionType, act);
		if (collisionType == CollisionGroup.TOP_BOTTOM_COLLISION){
			sprite1.setY(this.getY()-sprite1.getHeight()-1);
		}
		
		customAction (sprite1, this, collisionType, act); 
	}
}
