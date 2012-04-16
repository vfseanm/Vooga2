package editor.frameworks;

import java.util.List;

import sprite.AnimatedGameSprite;

public interface Framework {

   
    public AnimatedGameSprite getSprite(int x, int y);
    
    //public ArrayList<AnimatedGameSprite> getSprites();
    public void updateSprites(List<Object> parameters);
    
    public String getType();
   
    
}
