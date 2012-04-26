package attributes.fighterattributes;

import java.awt.event.KeyEvent;

import attributes.Attribute;
import attributes.interfaces.Updateable;

import editor.json.Jsonable;


// FINISH IMPLEMENTING
@SuppressWarnings("serial")
public class Shoot extends Attribute implements Updateable {

    
    
    
	@Override
	public String getName() {
		return "Shoot";
	}


	public void invert() {
		// TODO Auto-generated method stub

	}

	public void update(long elapsedTime) {	
		if (!myCanFire) {
			myCanFire = myRefireRate.action(elapsedTime);
		}
		
		if (myUserInput.isKeyPressed((KeyEvent.VK_SPACE)) && myCanFire) {
			double horizDistance = myTarget.getX() - myGameCharacter.getX();
			double vertDistance = myTarget.getY() - myGameCharacter.getY();
			double ratio = vertDistance / horizDistance;

			if (horizDistance < 0)
				myGameCharacter.getMissile().setSpeed(-0.7, -0.7 * ratio);
			else
				myGameCharacter.getMissile().setSpeed(0.7, 0.7 * ratio);
			
			myCanFire = false;
		}
	}
	
	public Object clone()
	{
	    return new Shoot();
	}

    public static Shoot fromJson(String json)
    {
        return new Shoot();
    }
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
}
