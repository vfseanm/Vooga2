import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.golden.gamedev.object.*;

public class SpecificSpriteGroup<T extends Sprite> {
	List<T> mySprites = new ArrayList<T>();
	
	public void addSprite(T Sprite) {
		mySprites.add(Sprite);
	}
	
	public T getActive() {
		for (T sprite: mySprites) {
			if (sprite.isActive()) {
				return sprite;
			}
		}
		return null;
	}
	
	public void addAll(List<? extends T> toAdd) {
		for (T Sprite: toAdd) {
			addSprite(Sprite);
		}
	}
	
	public static void main(String[] args) {
		AnimatedSprite s = new AnimatedSprite();
		SpecificSpriteGroup<Sprite> group = new SpecificSpriteGroup<Sprite>();
		List<AnimatedSprite> sprites = new ArrayList<AnimatedSprite>();
		sprites.add(s);
		group.addAll(sprites);
	}
}
