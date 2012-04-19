package attributes;


// FINISH IMPLEMENTING
@SuppressWarnings("serial")
public class Shoot extends Attribute implements Updateable {

	@Override
	public String getName() {
		return "Shoot";
	}

	// if (canFire == false) {
	// canFire = refireRate.action(elapsedTime);
	// }
	//	
	// if (myGame.keyDown(KeyEvent.VK_SPACE) && canFire) {
	// Sprite missile = new Sprite(myGame.getImage("img/Missile.png"),
	// getX()+23, getY()+23);
	// if (!(myGame.getLevel() instanceof Level2)) myGame.addMissile(missile);
	// else {
	// myGame.addAntiCupidMissile(missile);
	// }
	// canFire = false;
	// refireRate.refresh();
	// }

	public void invert() {
		// TODO Auto-generated method stub

	}

	public void update(long elaspedTime) {
		// TODO Auto-generated method stub

	}
	
	public Object clone()
	{
	    return new Shoot();
	}
}
