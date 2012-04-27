package weapons.fighterweapons;

import java.util.List;
import java.util.ResourceBundle;

import playfield.SingletonSpriteManager;

import attributes.interfaces.Input;

import character.AttributeUser;

import com.golden.gamedev.engine.BaseInput;
import enemies.Enemy;
import sprite.*;
import weapons.Weapon;

public class FighterTargetedMissile implements Weapon, Input{

	transient protected ResourceBundle myGameKeys = ResourceBundle
    .getBundle("demo.GameKeysResourceBundle");
	
	private AnimatedGameSprite 			myMissile;
	@SuppressWarnings("unused")
	private List<Enemy>					myTargets;
	@SuppressWarnings("unused")
	private Enemy						myClosestEnemy;
	private double[]					closestEnemyDistance;
	@SuppressWarnings("unused")
	private Enemy						myFurthestEnemy;
	private double[]					furthestEnemyDistance;
	private BaseInput 					myUserInput;
 	@SuppressWarnings("unused")
	private int 						myDamage;
	private int 						myDelay;
	private int 						myTimer;
	@SuppressWarnings("unused")
	private double 						mySpeed;
	private boolean						fireAtClosestOrFurthest;
	private boolean 					canFire;
	private int							shootKey;

	
	public FighterTargetedMissile(AnimatedGameSprite missile, int damage, int delay,
			double speed, boolean value) {
		myMissile = missile;
		myDamage = damage;
		myDelay = delay;
		mySpeed = Math.abs(speed);
		myTimer = 0;
		canFire = true;
		fireAtClosestOrFurthest = value;
		shootKey = Integer.parseInt(myGameKeys.getString("SHOOT"));
	}

	public void use(AttributeUser character) {

		if (myTimer == 0 && myUserInput.isKeyPressed((shootKey)) && canFire) {
			SingletonSpriteManager.getInstance().add(myMissile);
			myMissile.setLocation(character.getX(), character.getY());
			
			@SuppressWarnings("unused")
			List<Enemy> myEnemies = SingletonSpriteManager.getInstance().getMyEnemies();
			

			canFire = false;
		} 
		else if (myTimer > myDelay) 
		{
			canFire = true;
			myTimer = 0;
		}
		myTimer++; 
	}
	
	public double[] calculateDistance(AttributeUser character, Enemy target) {
		double horizDistance = character.getX() - target.getX();
		double vertDistance = character.getY() - target.getY();
		double distance = Math.sqrt(Math.pow(horizDistance, 2) + Math.pow(vertDistance, 2));
		double ratio = vertDistance / horizDistance;
		double[] calcValues = {distance, ratio, horizDistance};
		return calcValues;
	}
	
	public void findClosestAndFurthestEnemies(AttributeUser character, List<Enemy> myEnemies) 
	{
		closestEnemyDistance = calculateDistance(character, myEnemies.get(0));
		furthestEnemyDistance = calculateDistance(character, myEnemies.get(0));
		
		for (int i = 1; i < myEnemies.size(); i++) 
		{
			double[] indexDistanceInfo = calculateDistance(character, myEnemies.get(0));
			if (indexDistanceInfo[0] < closestEnemyDistance[0])
			{
				closestEnemyDistance = indexDistanceInfo;
				myClosestEnemy = myEnemies.get(i);
			}
			if (indexDistanceInfo[0] > furthestEnemyDistance[0])
			{
				furthestEnemyDistance = indexDistanceInfo;
				myFurthestEnemy = myEnemies.get(i);
			}
		}
	}

	
	public void invert() {
		fireAtClosestOrFurthest = !fireAtClosestOrFurthest;
	}

	public void setUserInput(BaseInput userInput) {
		myUserInput = userInput;
	}
	

}
