package sprite;

import java.util.ArrayList;
import java.util.List;

import platforms.Platform;
import powerUps.PowerUp;

import collisions.EnemyPlatformCollisionManager;
import collisions.EnemyPowerUpCollisionManager;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.CollisionGroup;

public class GameSpriteGroup {
	private SpriteGroup enemyGroup = new SpriteGroup("enemy");
	private SpriteGroup platformGroup = new SpriteGroup ("platform");
	private SpriteGroup powerGroup = new SpriteGroup ("power");
	
	private SpriteGroup fighterGroup = new SpriteGroup("hero"); 
	
	private static GameSpriteGroup instanceSpriteGroup = new GameSpriteGroup(); 
	private PlayField pen = new PlayField ();

	ArrayList<CollisionGroup> managerList = new ArrayList<CollisionGroup>();

	private GameSpriteGroup(){
		super();
	}
	
	public void addSprite (GameSprite mySprite){
		if (mySprite.getClass()== PowerUp.class)
			powerGroup.add(mySprite);
		if (mySprite.getClass() == Enemy.class)
			enemyGroup.add(mySprite);
		if (mySprite.getClass() == Platform.class)
			platformGroup.add(mySprite);
	}
	
	public SpriteGroup getEnemyGroup (){
		return enemyGroup;
	}
	
	public SpriteGroup getPlatformGroup (){
		return platformGroup;
	}
	
	public SpriteGroup getPowerGroup (){
		return powerGroup;
	}
	
	public static GameSpriteGroup getInstance (){
		return instanceSpriteGroup; 
	}
	
	public SpriteGroup getFighterGroup(){
		return fighterGroup;
	}
	
	public List<CollisionGroup> createManagers (){		
		if (managerList.isEmpty()){
			EnemyPlatformCollisionManager enTplat = new EnemyPlatformCollisionManager();
			enTplat.setCollisionGroup(enemyGroup, platformGroup);		
			managerList.add(enTplat);
			pen.addCollisionGroup(enemyGroup, platformGroup, enTplat);
			
			EnemyPowerUpCollisionManager enTpow = new EnemyPowerUpCollisionManager();
			enTpow.setCollisionGroup(enemyGroup, powerGroup);
			managerList.add(enTpow);
			pen.addCollisionGroup(enemyGroup, platformGroup, enTpow);
		}
		return managerList;
	}

}
