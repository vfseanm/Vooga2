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


import sprite.AnimatedGameSprite;


public class Level implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -7585264855447840739L;
    
    
    private List<AnimatedGameSprite> sprites;

  
    
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
    
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();
        updateImages();
        
    }
    
    private void updateImages()
    {
        for(AnimatedGameSprite s: sprites)
        {
            BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
            BufferedImage[] images = new BufferedImage[s.getImageNames().size()];
            for(int i=0; i<images.length; i++)
            {
                images[i] = loader.getImage(s.getImageNames().get(i));
            }
            s.setImages(images);
        }
    }
    
/*    public static void main(String[] args)
    {
        String filename = "level.ser";
        Level lev = new Level();
        BaseLoader loader = new BaseLoader(new BaseIO(AbstractPlatform.class), Color.PINK);
        BufferedImage[] image = new BufferedImage[1];
        image[0] = loader.getImage("resources/block3.png");
        ArrayList<String> imageNames = new ArrayList<String>();
        imageNames.add("resources/block3.png");
        RotatingPlatform b = new RotatingPlatform(image,5 ,6,imageNames,  null );
        RotatingPlatform c = new RotatingPlatform(image, 7, 9, imageNames, null);
        lev.addPlatform(b);
        lev.addPlatform(c);
        
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try
        {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(lev);
            out.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        Level lev2;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try
        {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            lev2 = (Level)in.readObject();
            in.close();
            List<AbstractPlatform> platList = lev2.getPlatforms();
            System.out.println(lev2);
            for(AbstractPlatform x: platList)
            {
                System.out.println(x.getX());
            }
            
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }*/
   
}

