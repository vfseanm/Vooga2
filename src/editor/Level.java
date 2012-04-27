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

/**
 * Stores and manipulates information about the level.
 * Serves as the Model in the Model View Controller of the level editor
 * @author Becky and Sean
 *
 */
public class Level implements Serializable{

    private static final long serialVersionUID = -7585264855447840739L;
    
    
    //private List<AnimatedGameSprite> sprites;
    private List<Framework> frameworks;
    private String backgroundImagePath;
    private ImageBackground myBackground;
    private Fighter myFighter;
    private Sidescroller mySidescroller; // THERE SHOULD BE SOME DEFAULT SIDESCROLLING !!

  
    /**
     * initializes the level
     */
    public Level()
    {
        frameworks = new ArrayList<Framework>();
        backgroundImagePath = "";
       // sprites = new ArrayList<AnimatedGameSprite>();
    }
    /**
     * replaces one sprite with another
     * @param oldSprite sprite to be replaced
     * @param newSprite sprite that will replaced
     */
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
    
    /**
     * 
     * @return an unmodifiable list of the frameworks
     */
    public List<Framework> getFrameworks()
    {
        return Collections.unmodifiableList(frameworks);
    }
    
   /**
    * moves all of the sprites horizontally by the specified distance 
    * @param x distance to move sprites
    */
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
    
    /**
     * sets the side scroller in the level
     * @param scroller to be set
     */
    public void setSidescrolling(Sidescroller scroller)
    {
        mySidescroller = scroller;
    }
    
    /**
     * move all of the sprites vertically by the specified distance
     * @param y distance to be moved
     */
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
    /**
     * 
     * @return the sidescroller currently stored
     */
    public Sidescroller getSidescroller()
    {
        if (mySidescroller ==null)
        {
            mySidescroller = new ConcreteSidescroller();
        }
            
        return mySidescroller;
    }
    /**
     * clear the editor
     */
    public void clear()
    {
        frameworks.clear();
        myFighter = null;
    }
    
    /**
     * 
     * @return an unmodifiable list of all of the sprites in the level
     */
    public List<AnimatedGameSprite> getAllSprites()
    {
        
        return Collections.unmodifiableList(getSprites());
    }
    /**
     * 
     * @return the sprites in the editor
     */
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
    /**
     * remove the specified sprite from the level
     * @param sprite to be removed
     */
    public void removeSprite(AnimatedGameSprite sprite)
    {
        for(Framework f: frameworks)
        {
            if(f.containsSprite(sprite))
            {
                f.removeSprite(sprite);
            }
        }
    }
    /**
     * 
     * @return the fighter
     */
    public Fighter getFighter()
    {
        return myFighter;
    }
    
    /**
     * set a location of a particular sprite
     * @param sprite to be set
     * @param x coordinate to set
     * @param y coordinate to set
     */
    public void setSpriteLocation(AnimatedGameSprite sprite, double x, double y)
    {
        sprite.setLocation(x, y);
    }
    
    /**
     * Set the fighter as specified 
     * @param fighter to be set
     */
    public void setFighter(Fighter fighter)
    {
        myFighter = fighter;
    }
    /**
     * called when java's default serialization is being read from
     * @param stream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();
        updateImages();
    }
    
    /**
     * update all of the images when the images are to be loaded
     */
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
    
    /**
     * set the background image
     * @param image to set in the background
     * @param imagePath the path of the image to be stored
     */
    public void setBackground(BufferedImage image, String imagePath)
    {
        backgroundImagePath = imagePath;
        myBackground = new ImageBackground(image);
    }
    /**
     * 
     * @return the bakcground of the level
     */
    public ImageBackground getBackground()
    {
        return myBackground;
    }
    
    /**
     * add a framework to the level
     * @param f framework to be added
     */
    public void addFramework(Framework f)
    {
        frameworks.add(f);
    }
    
    /**
     * writing the level to json
     * @return String to write to json
     */
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
    /**
     * takes a json string and creates a level
     * @param json string to be loaded
     * @return Level that is created fromJson
     */
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

