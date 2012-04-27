package weapons.fighterweapons;

import java.util.List;

import playfield.SingletonSpriteManager;

import attributes.interfaces.Input;

import character.AttributeUser;

import com.golden.gamedev.engine.BaseInput;

import demo.SingletonKeyController;
import enemies.Enemy;
import sprite.*;
import weapons.Weapon;

public class FighterTargetedMissile implements Weapon, Input {

	private AnimatedGameSprite myMissile;
	@SuppressWarnings("unused")
	private List<Enemy> myTargets;
	@SuppressWarnings("unused")
	private Enemy myClosestEnemy;
	private double[] closestEnemyDistance;
	@SuppressWarnings("unused")
	private Enemy myFurthestEnemy;
	private double[] furthestEnemyDistance;
	private BaseInput myUserInput;
	@SuppressWarnings("unused")
	private int myDamage;
	private int myDelay;
	private int myTimer;
	private double mySpeed;
	private boolean fireAtClosest;
	private boolean canFire;

	public FighterTargetedMissile(AnimatedGameSprite missile, int damage,
			int delay, double speed, boolean value) {
		myMissile.setGroup("FIGHTERMISSILE");
		myMissile = missile;
		myDamage = damage;
		myDelay = delay;
		mySpeed = Math.abs(speed);
		myTimer = 0;
		canFire = true;
		fireAtClosest = value;
	}

	@SuppressWarnings("unused")
	public void use(AttributeUser character) {
		if (myTimer == 0 && myUserInput.isKeyPressed((SingletonKeyController.getInstance().getKeyCode(("SHOOT")))) && canFire) {
			SingletonSpriteManager.getInstance().add(myMissile);
			myMissile.setLocation(character.getX(), character.getY());

			List<Enemy> myEnemies = SingletonSpriteManager.getInstance()
					.getMyEnemies();

			if (fireAtClosest) fireAtEnemy(closestEnemyDistance);
			else fireAtEnemy(furthestEnemyDistance);

			canFire = false;
		} else if (myTimer > myDelay) {
			canFire = true;
			myTimer = 0;
		}
		myTimer++;
	}

	public double[] calculateDistance(AttributeUser character, Enemy target) {
		double horizDistance = character.getX() - target.getX();
		double vertDistance = character.getY() - target.getY();
		double distance = Math.sqrt(Math.pow(horizDistance, 2)
				+ Math.pow(vertDistance, 2));
		double ratio = vertDistance / horizDistance;
		double[] calcValues = { distance, ratio, horizDistance };
		return calcValues;
	}

	public void findClosestAndFurthestEnemies(AttributeUser character,
			List<Enemy> myEnemies) {
		closestEnemyDistance = calculateDistance(character, myEnemies.get(0));
		furthestEnemyDistance = calculateDistance(character, myEnemies.get(0));

		for (int i = 1; i < myEnemies.size(); i++) {
			double[] indexDistanceInfo = calculateDistance(character, myEnemies
					.get(0));
			if (indexDistanceInfo[0] < closestEnemyDistance[0]) {
				closestEnemyDistance = indexDistanceInfo;
				myClosestEnemy = myEnemies.get(i);
			}
			if (indexDistanceInfo[0] > furthestEnemyDistance[0]) {
				furthestEnemyDistance = indexDistanceInfo;
				myFurthestEnemy = myEnemies.get(i);
			}
		}
	}

	public void fireAtEnemy(double[] distanceInfo) {
		if (distanceInfo[2] < 0) {
			myMissile.setSpeed(-mySpeed, -mySpeed * distanceInfo[1]);
		} else {
			myMissile.setSpeed(mySpeed, mySpeed * distanceInfo[1]);
		}
		SingletonSpriteManager.getInstance().add(myMissile);
	}

	public void invert() {
		fireAtClosest = !fireAtClosest;
	}

	public void setUserInput(BaseInput userInput) {
		myUserInput = userInput;
	}

}