package editor.frameworks;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import enemies.Enemy;


import sprite.AnimatedGameSprite;

public class Framework implements Serializable {
    protected List<AnimatedGameSprite> mySprites;
    //transient protected BufferedImage[] myImages;
    //protected List<String> imageNames;
    private AnimatedGameSprite prototypeSprite;
    private String myType;
    
    public Framework(String type, AnimatedGameSprite s)
    {
        prototypeSprite = s;
        myType = type;
        mySprites = new ArrayList<AnimatedGameSprite>();
    }
   
    public AnimatedGameSprite getSprite(int x, int y)
    {
        AnimatedGameSprite s = (AnimatedGameSprite) prototypeSprite.clone();
        s.setX(x);
        s.setY(y);
        mySprites.add(s);
        return s;
    }
    
    //public ArrayList<AnimatedGameSprite> getSprites();
    public void updateSprites(AnimatedGameSprite sprite)
    {
        List<AnimatedGameSprite> newSprites = new ArrayList<AnimatedGameSprite>();
        prototypeSprite = sprite;
        System.out.println(prototypeSprite.getImageNames());
        for(AnimatedGameSprite s: mySprites)
        {
            
            AnimatedGameSprite toAdd =(AnimatedGameSprite) prototypeSprite.clone();
            toAdd.setX(s.getX());
            toAdd.setY(s.getY());
            newSprites.add(toAdd);        
            
        }
        mySprites = newSprites;
    }
    
    public String getType()
    {
        return myType;
    }

    
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
        mySprites.add( s);
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
        
    /*    myImages = new BufferedImage[imageNames.size()];
        for(int i=0; i<myImages.length; i++)
        {
            //System.out.println("image names: "+s.getImageNames());
            myImages[i] = loader.getImage(imageNames.get(i));
        }
        */
        
    }
    
    public AnimatedGameSprite getPrototype()
    {
        return prototypeSprite;
    }
    
/*    public String toJson()
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        
        List<String> list = new ArrayList<String>();
        list.add(prototypeSprite.getClass().toString());
        list.add(prototypeSprite.toJson());
        
        List<String> spriteList = new ArrayList<String>();
        for(AnimatedGameSprite s: mySprites)
        {
            List<String> position = new ArrayList<String>();
            position.add(s.getX()+"");
            position.add(s.getY()+"");
            spriteList.add(gson.toJson(position));
        }
        list.add(gson.toJson(spriteList));
        
        return gson.toJson(list);
        
        
    }*/
   
    
}
