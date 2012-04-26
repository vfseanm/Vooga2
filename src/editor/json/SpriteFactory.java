package editor.json;

import sprite.AnimatedGameSprite;

public class SpriteFactory<T extends AnimatedGameSprite & Jsonable> {
    T mySprite;
    public SpriteFactory(T s)
    {
        mySprite =s;
    }
    
    public boolean isThisKindOfSprite(String s)
    {
        return mySprite.getClass().toString().equals(s);
    }
    
    public AnimatedGameSprite parseFromJson(String json)
    {
        return (AnimatedGameSprite) (mySprite).fromJson(json);
    }
}
