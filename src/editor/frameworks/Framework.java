package editor.frameworks;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;

import enemies.Enemy;

import bonusobjects.PowerUp;

import sprite.AnimatedGameSprite;

public abstract class Framework {
    protected List<AnimatedGameSprite> mySprites;
   
    public abstract AnimatedGameSprite getSprite(int x, int y);
    
    //public ArrayList<AnimatedGameSprite> getSprites();
    public abstract void updateSprites(List<Object> parameters);
    
    public abstract String getType();

    
    public void moveHorizontally(double x)
    {
        for (Sprite s : mySprites)
        {
            s.setX(s.getX() + x);
        }
    }
    
    public void moveVertically(double y)
    {
        for(Sprite s: mySprites)
        {
            s.setY(s.getY()+y);
        }
    }
    
    public List<AnimatedGameSprite> getAllSprites()
    {
        return Collections.unmodifiableList(mySprites);
    }
    
    public boolean containsSprite(AnimatedGameSprite s)
    {
        return mySprites.contains(s);
    }
    public void removeSprite(AnimatedGameSprite s)
    {
        mySprites.remove(s);
    }
    public void addSprite(AnimatedGameSprite s)
    {
        mySprites.add((Enemy) s);
    }
    
    public List<AnimatedGameSprite> getSprites()
    {
        return mySprites;
    }
    
    public void updateImages()
    {
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
        for(AnimatedGameSprite s: mySprites)
        {
            
            BufferedImage[] images = new BufferedImage[s.getImageNames().size()];
            for(int i=0; i<images.length; i++)
            {
                //System.out.println("image names: "+s.getImageNames());
                images[i] = loader.getImage(s.getImageNames().get(i));
            }
            s.setImages(images);
         }
    }
   
    
}
