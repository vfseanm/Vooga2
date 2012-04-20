package editor;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.List;


import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.frameworks.Framework;
import fighter.Fighter;


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
        
        //sprites.remove(oldSprite);
        //sprites.add(newSprite);
    }
    
    public void addSprite(AnimatedGameSprite s, Framework f)
    {
        
        if(!frameworks.contains(f))
        {
            frameworks.add(f);
        }
        f.addSprite(s);
        
    }
   
    public void moveHorizontally(double x)
    {
        for (Framework f : frameworks)
        {
            f.moveHorizontally(x);
        }
    }
    
    public void moveVertically(double y)
    {
        for(Framework f: frameworks)
        {
            f.moveVertically(y);
        }
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
                f.removeSprite(sprite);
            }
        }
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
            //System.out.println("image names: "+s.getImageNames());
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
    
    public Fighter getFighter()
    {
        return myFighter;
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
        ArrayList<String> frameworkList = new ArrayList<String>();
        for(Framework f: frameworks)
        {
           frameworkList.add(f.toJson());
        }
        
        myList.add(gson.toJson(frameworkList));
        return gson.toJson(myList);
        
    }
    
    public void fromJson(String json)
    {
        Gson gson = new Gson();

        Type collectionType = new TypeToken<ArrayList<String>>(){}.getType();

        ArrayList<String> myList = gson.fromJson(json, collectionType); 
        String backgroundImageName = myList.get(0);
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
        if(!backgroundImageName.equals(""))
        {
            setBackground(loader.getImage(backgroundImageName),backgroundImageName);
        }
        
       String fighterJson = myList.get(1);
       if(!fighterJson.equals(""))
        {
           //setFighter(Fighter.fromJson(fighterJson));
        }
       
        
        ArrayList<String> frameworkList = gson.fromJson(myList.get(2), collectionType);
        //System.out.println("framework LIst: "+frameworkList);
        
        for(String f: frameworkList)
        {
            addFramework(Framework.fromJson(f));
        }
        
        
        
        
    }
    
    public static void main(String[] args)
    {
        Level level = new Level();
        Scanner scanner;
        try
        {
            scanner = new Scanner(new File("Becky"));
            String wholeFile = scanner.useDelimiter("\\A").next();
            //System.out.println(wholeFile);
            level.fromJson(wholeFile);
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

   
}

