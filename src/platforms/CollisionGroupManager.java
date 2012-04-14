package platforms;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;


public class CollisionGroupManager extends AdvanceCollisionGroup {

	public CollisionGroupManager() {};
	
	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		return;	
	}
	
	public boolean isColliding(Sprite sprite1, Sprite sprite2) {
		SpriteGroup group2 = new SpriteGroup("sprite2");
		group2.add(sprite2);
		return isLeftRightCollisionCheck(sprite1, group2) || isRightLeftCollisionCheck(sprite1, group2) || isTopBottomCollisionCheck(sprite1, group2) || isBottomTopCollisionCheck(sprite1, group2);
	}
	
	public void collisionCheckHelper(Sprite sprite, SpriteGroup group2) {
		SpriteGroup group1 = new SpriteGroup("sprite");
		group1.add(sprite);
		setCollisionGroup(group1, group2);
		checkCollision();
	}
	
	public boolean isLeftRightCollisionCheck(Sprite sprite, SpriteGroup group2) {
		collisionCheckHelper(sprite, group2);
		return getCollisionSide() == LEFT_RIGHT_COLLISION;
	}
	
	public boolean isRightLeftCollisionCheck(Sprite sprite, SpriteGroup group2) {
		collisionCheckHelper(sprite, group2);
		return getCollisionSide() == RIGHT_LEFT_COLLISION;
	}
	
	public boolean isBottomTopCollisionCheck(Sprite sprite, SpriteGroup group2) {
		collisionCheckHelper(sprite, group2);
		return getCollisionSide() == BOTTOM_TOP_COLLISION;
	}
	
	public boolean isTopBottomCollisionCheck(Sprite sprite, SpriteGroup group2) {
		collisionCheckHelper(sprite, group2);
		return getCollisionSide() == TOP_BOTTOM_COLLISION;
	}
}
