package fighter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import sprite.*;
import attributes.*;
import com.golden.gamedev.*;


@SuppressWarnings("serial")
public class Fighter extends GameSprite {

	private List<Attribute>		myInherentAttributes;
	private List<Attribute>		myExtraAttributes;
	
	
	public Fighter(BufferedImage image, double x, double y, String imagePath, ArrayList<Attribute> inherentAttributes) {
		super(image, x, y, imagePath);
		myInherentAttributes = inherentAttributes;
	}
	
	public boolean containsAttribute(Class attribute) {
	    for (Attribute ability: myInherentAttributes) {
	        if (ability.getClass() == attribute) {
	            return true;
	        }
	    }
        return false;
	}
	
	public void addExtraAttribute(Attribute extra) {
	    myExtraAttributes.add(extra);
	}
	
	public void checkBounds() {
	}
	
	
	public void updateSpeed() {
	}
	
	
	public void fireMissile(long elapsedTime) {
		if (canFire == false) {
			canFire = refireRate.action(elapsedTime);
		}
		
		if (myGame.keyDown(KeyEvent.VK_SPACE) && canFire) {
			if (canFireExtra) bonusFire();
			else regularFire();
		}
		
		if (extraFire.action(elapsedTime)) {
			canFireExtra = false;
		}
	}
	
	public void regularFire() {
		Sprite missile = new Sprite(myGame.getImage("img/Missile.png"),
				getX()+23, getY()+23);
		if (!(myGame.getLevel() instanceof Level2)) myGame.addMissile(missile);
		else {
			myGame.addAntiCupidMissile(missile);
		}
		canFire = false;
		refireRate.refresh();
	}
	
	public void bonusFire() {
		Sprite missile = new Sprite(myGame.getImage("img/Missile.png"),
				getX()+11, getY()+23);
		Sprite missile2 = new Sprite(myGame.getImage("img/Missile.png"),
				getX()+35, getY()+23);
		if (!(myGame.getLevel() instanceof Level2)) {
			myGame.addMissile(missile);
			myGame.addMissile(missile2);
		}
		else {
			myGame.addAntiCupidMissile(missile);
			myGame.addAntiCupidMissile(missile2);
		}
		canFire = false;
		refireRate.refresh();
	}

	public void update(long elapsedTime) {
		updateSpeed();
		fireMissile(elapsedTime);
		checkBounds();
	}
	
	public void collide() {
		myHealth--;
		if (myHealth == 0) {
			myGame.fighterDies();
		}
	}
	
	public int getHealth() {
		return myHealth;
	}
	
	public void gainHealth() {
		myHealth++;
	}
	
	public void extraShot() {
		extraFire.refresh();
		canFireExtra = true;
	}

}
