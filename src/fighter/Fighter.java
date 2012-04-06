package fighter;

import sprite.*;

import java.awt.image.BufferedImage;
import java.util.List;

import attributes.Attribute;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.sprite.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.object.collision.*;
import com.golden.gamedev.util.*;
import com.golden.gamedev.funbox.*;

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
	
	public void checkBounds() {
		if (getX() < 0)
			setLocation(0, getY());
		if (getY() < 0) {
			if (!(myLevel instanceof Level2))
				myLevel = myGame.levelUp();
			else setLocation(getX(), 0);
		}
		if (getY() > 480-getHeight()) 
			setLocation(getX(), 480-getHeight());
		if (getX() > 640-getWidth())
			setLocation(640-getWidth(), getY());
	}
	
	
	public void updateSpeed() {
		if (myGame.keyDown(KeyEvent.VK_UP)) {
			setLocation(getOldX(),getOldY()-0.7);
		}
		else if (myGame.keyDown(KeyEvent.VK_DOWN)) setLocation(getOldX(),getOldY()+0.7);;
		
		if (myGame.keyDown(KeyEvent.VK_LEFT)) setLocation(getOldX()-0.7,getOldY());
		else if (myGame.keyDown(KeyEvent.VK_RIGHT)) setLocation(getOldX()+0.7,getOldY());
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
