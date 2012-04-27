package editor;

import java.awt.Color;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.background.ImageBackground;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import fighter.Fighter;


import sidescrolling.ConcreteSidescroller;
import sidescrolling.Sidescroller;
import sprite.AnimatedGameSprite;


public class Level implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -7585264855447840739L;
    
    
    //private List<AnimatedGameSprite> sprites;
    private List<Framework> frameworks;
    private String backgroundImagePath;
    private ImageBackground myBackground;
    private Fighter myFighter;
    private Sidescroller mySidescroller; // THERE SHOULD BE SOME DEFAULT SIDESCROLLING !!

  
    
    public Level()
    {
        frameworks = new ArrayList<Framework>();
        backgroundImagePath = "";
       // sprites = new ArrayList<AnimatedGameSprite>();
    }
    
    public void replaceSprite(AnimatedGameSprite oldSprite, AnimatedGameSprite newSprite)
    {
        for(Framework f: frameworks)
        {
            if(f.containsSprite(oldSprite))
            {
                f.removeSprite(oldSprite);
                f.addSprite(newSprite);
            }
        }

    }
    

    public List<Framework> getFrameworks()
    {
        return Collections.unmodifiableList(frameworks);
    }
   
    public void moveHorizontally(double x)
    {
        for (Framework f : frameworks)
        {
            f.moveHorizontally(x);
        }
        if(myFighter!=null)
        {
            myFighter.moveX(x);
        }
    }
    
    public void setSidescrolling(Sidescroller scroller)
    {
        mySidescroller = scroller;
    }
    
    public void moveVertically(double y)
    {
        for(Framework f: frameworks)
        {
            f.moveVertically(y);
        }
        if(myFighter!=null)
        {
            myFighter.moveY(y);
        }
    }
    
    public Sidescroller getSidescroller()
    {
        if (mySidescroller ==null)
        {
            mySidescroller = new ConcreteSidescroller();
        }
            
        return mySidescroller;
    }
    
    public void clear()
    {
        frameworks.clear();
        myFighter = null;
    }
    
    public List<AnimatedGameSprite> getAllSprites()
    {
        
        return Collections.unmodifiableList(getSprites());
    }
    
    public List<AnimatedGameSprite> getSprites()
    {
        List<AnimatedGameSprite> sprites = new ArrayList<AnimatedGameSprite>();
        for(Framework f: frameworks)
        {
            sprites.addAll(f.getSprites());
        }
        if(myFighter!=null)
        {
            sprites.add(myFighter);
        }
        return sprites;
    }
    
    public void removeSprite(AnimatedGameSprite sprite)
    {
        for(Framework f: frameworks)
        {
            if(f.containsSprite(sprite))
            {
                System.out.println("removing sprite");
                f.removeSprite(sprite);
            }
        }
    }
    public Fighter getFighter()
    {
        return myFighter;
    }
    
    public void setSpriteLocation(AnimatedGameSprite sprite, double x, double y)
    {
        sprite.setLocation(x, y);
    }
    public void setFighter(Fighter fighter)
    {
        //sprites.add(fighter);
        myFighter = fighter;
    }
    
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();
        updateImages();
        
        
    }
    
    private void updateImages()
    {
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
        for(Framework f: frameworks)
        {
            f.updateImages();
        }
        if(!backgroundImagePath.equals(""))
        {
            myBackground.setImage(loader.getImage(backgroundImagePath));
        }
        if(myFighter!=null)
        {
        BufferedImage[] images = new BufferedImage[myFighter.getImageNames().size()];
        for(int i=0; i<images.length; i++)
        {
            images[i] = loader.getImage(myFighter.getImageNames().get(i));
        }
        myFighter.setImages(images);
        }
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
    

    public void addFramework(Framework f)
    {
        frameworks.add(f);
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        ArrayList<String> myList = new ArrayList<String>();
        myList.add(backgroundImagePath);
        
        if(myFighter!=null)
        {
            myList.add(myFighter.toJson());
        }
        else
        {
            myList.add("");
        }
        if(mySidescroller!=null)
        {
            myList.add(mySidescroller.toJson());
        }
        else
        {
            myList.add("");
        }
        ArrayList<String> frameworkList = new ArrayList<String>();
        for(Framework f: frameworks)
        {
           frameworkList.add(f.toJson());
           
        }
        
        myList.add(gson.toJson(frameworkList));
        return gson.toJson(myList);
        
    }
    
    public static Level fromJson(String json)
    {
        Gson gson = new Gson();
        Level level = new Level();
        Type collectionType = new TypeToken<ArrayList<String>>(){}.getType();
        

        ArrayList<String> myList = gson.fromJson(json, collectionType); 
        String backgroundImageName = myList.get(0);
        BaseLoader loader = new BaseLoader(new BaseIO(Level.class), Color.BLACK);
        if(!backgroundImageName.equals(""))
        {
            
            level.setBackground(loader.getImage(backgroundImageName),backgroundImageName);
        }
        String fighterJson = myList.get(1);
        if(!fighterJson.equals(""))
        {
           level.setFighter((Fighter) Fighter.getFactory().parseFromJson(fighterJson));
           
        }
       
        String scrollerJson = myList.get(2);
        if(!scrollerJson.equals(""))
        {
            level.setSidescrolling(Sidescroller.fromJson(scrollerJson));
        }
       
        
        ArrayList<String> frameworkList = gson.fromJson(myList.get(3), collectionType);
        
        for(String f: frameworkList)
        {
            level.addFramework(Framework.fromJson(f));
        }
        return level;     
        
    }
    


   
}

