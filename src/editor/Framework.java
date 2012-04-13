package editor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import sprite.AnimatedGameSprite;

public interface Framework {

   
    public AnimatedGameSprite getSprite(int x, int y);
    
    //public ArrayList<AnimatedGameSprite> getSprites();
    public void updateSprites(List<Object> parameters);
    
}
