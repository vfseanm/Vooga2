package editor;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;

import enemies.Enemy;


import platforms.AbstractPlatform;
import platforms.RotatingPlatform;
import powerUps.PowerUp;
import sprite.AnimatedGameSprite;
import sprite.GameSprite;


public class Level implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -7585264855447840739L;
    
    
    private List<AbstractPlatform> platforms;
    private List<PowerUp> powerUps;
    private List<Enemy> enemies;
    

    
    
    public Level()
    {
        
        platforms = new ArrayList<AbstractPlatform>();
        powerUps = new ArrayList<PowerUp>();
        enemies = new ArrayList<Enemy>();
        
        
    }
    
    public void addPlatform(AbstractPlatform p)
    {
        platforms.add(p);
    }
    
    public void addPowerUp(PowerUp p)
    {
        powerUps.add(p);
    }
    
    public void addEnemy(Enemy e)
    {
        enemies.add(e);
    }

    public List<AbstractPlatform> getPlatforms()
    {
        return platforms;
    }
    
    public List<GameSprite> getAllSprites()
    {
        List<AnimatedGameSprite> list = new ArrayList<AnimatedGameSprite>();
        list.addAll(platforms);
        list.addAll(enemies);
        list.addAll(powerUps);
        return list;
    }
    public static void main(String[] args)
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
        
        
        
        
    }
   
    

    
}

