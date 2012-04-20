package platforms.platformtypes;

import collisions.CustomActionPerformer;

import com.golden.gamedev.object.collision.CollisionGroup;

import enemies.Enemy;


public class BreakablePlatform extends DecoratedPlatform {

	private static final long serialVersionUID = 1254073087890380273L;
	
	public BreakablePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
	}
	
	public void doBehavior(double speed, double distance) {
		return;
	}
	
	public void doBreak() {
		//only called if colliding top/bottom with fighter
		if (myDecoratorComponent != null) {
			myDecoratorComponent.doBreak();
		}
		releaseItem();
		setActive(false);
	}	
	
	public String toString() {
		return "side to side" + myDecoratorComponent.toString();
	}
	public Object clone()
	{
	    AbstractPlatform toWrap = null;
	    if(myDecoratorComponent!=null)
	    {
	        toWrap = (AbstractPlatform) myDecoratorComponent.clone();
	        
	    }
	    return new BreakablePlatform(toWrap);
	    
	}
	
	public void action (Enemy sprite1, int collisionType, CustomActionPerformer act){
		standardAction (sprite1, collisionType, act);
		if (collisionType == CollisionGroup.BOTTOM_TOP_COLLISION){
			this.doBreak();
		}
		customAction (sprite1, this, collisionType, act); 
	}

}
