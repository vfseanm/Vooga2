package editor;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;


import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;

import fighter.Fighter;


import sprite.AnimatedGameSprite;


public class Level implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -7585264855447840739L;
    
    
    private List<AnimatedGameSprite> sprites;
    private String backgroundImagePath;
    private ImageBackground myBackground;

  
    
    public Level()
    {
        
        sprites = new ArrayList<AnimatedGameSprite>();
    }
    
    public void replaceSprite(AnimatedGameSprite oldSprite, AnimatedGameSprite newSprite)
    {
        sprites.remove(oldSprite);
        sprites.add(newSprite);
    }
    
    public void addSprite(AnimatedGameSprite s)
    {
        sprites.add(s);
    }
   
    public void moveHorizontally(double x)
    {
        for (Sprite s : sprites)
        {
            s.setX(s.getX() + x);
        }
    }
    
    public void moveVertically(double y)
    {
        for(Sprite s: sprites)
        {
            s.setY(s.getY()+y);
        }
    }
    
    public void clear()
    {
        sprites.clear();
    }
    
    public List<AnimatedGameSprite> getAllSprites()
    {
        return Collections.unmodifiableList(sprites);
    }
    
    public List<AnimatedGameSprite> getSprites()
    {
        return sprites;
    }
    
    public void removeSprite(AnimatedGameSprite sprite)
    {
        sprites.remove(sprite);
    }
    
    public void setSpriteLocation(AnimatedGameSprite sprite, double x, double y)
    {
        sprite.setLocation(x, y);
    }
    public void setFighter(Fighter fighter)
    {
        sprites.add(fighter);
    }
    
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();
        updateImages();
        
    }
    
    private void updateImages()
    {
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
        for(AnimatedGameSprite s: sprites)
        {
            
            BufferedImage[] images = new BufferedImage[s.getImageNames().size()];
            for(int i=0; i<images.length; i++)
            {
                //System.out.println("image names: "+s.getImageNames());
                images[i] = loader.getImage(s.getImageNames().get(i));
            }
            s.setImages(images);
         }
        myBackground.setImage(loader.getImage(backgroundImagePath));
    }
    
    public void setBackground(BufferedImage image, String imagePath)
    {
        backgroundImagePath = imagePath;
        myBackground = new ImageBackground(image);
    }
    
    public ImageBackground getBackground()
    {
        return myBackground;
    }
   
}

