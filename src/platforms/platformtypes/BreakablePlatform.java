package platforms.platformtypes;

import bonusobjects.BonusObject;


public class BreakablePlatform extends DecoratedPlatform {

	private static final long serialVersionUID = 1254073087890380273L;
	private BonusObject myBonusObject;
	
	public BreakablePlatform(AbstractPlatform decoratorComponent) {
		super(decoratorComponent);
	}
	
	public void doBehavior(double speed, double distance) {
		return;
	}
	
	public void setBonusObject(BonusObject bonusObject) {
		myBonusObject = bonusObject;
		myBonusObject.setActive(false);
	}
	
	private void releaseItem() {
		if (myBonusObject != null) {
			myBonusObject.setActive(true);
			myBonusObject.setLocation(getX(), getY() + 20);
			myBonusObject.setHorizontalSpeed(0.02);	
		}
	}
	
	public void doBreak() {
		//only called if colliding top/bottom with fighter
		releaseItem();
		setActive(false);
	}	
	
	public String toString() {
		return myPlatformResources.getString("Breakable") + myDecoratorComponent.toString();
	}
	
	public Object clone() {
	    AbstractPlatform toWrap = null;
	    if(myDecoratorComponent!=null) {
	        toWrap = (AbstractPlatform) myDecoratorComponent.clone();        
	    }
	    return new BreakablePlatform(toWrap);    
	}
}
