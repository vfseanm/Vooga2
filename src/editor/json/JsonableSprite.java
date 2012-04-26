package editor.json;

import sprite.AnimatedGameSprite;

public interface JsonableSprite {
    public String toJson();
    public AnimatedGameSprite fromJson(String json);
}
