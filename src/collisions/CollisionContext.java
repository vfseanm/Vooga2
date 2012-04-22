package collisions;


import sprite.AnimatedGameSprite;


public class CollisionContext {
	AnimatedGameSprite sprite1, sprite2;
	int side;
	
	public void addSprite1 (AnimatedGameSprite sp){
		sprite1 = sp;
	}
	
	public void addSprite2 (AnimatedGameSprite sp){
		sprite2 = sp;
	}	
	
	public AnimatedGameSprite getOtherSprite (AnimatedGameSprite sprite){
		if (sprite==sprite1){
			return sprite2;
		}
		else
			return sprite1;
	}
	
	
	public void addSide (int side){
		this.side = side;
	}
	
	public int getSide(){
		return side;
	}
}
