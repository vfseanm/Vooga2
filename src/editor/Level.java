package editor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import platforms.AbstractPlatform;

import platforms.BreakablePlatform;
import powerUps.PowerUp;

import sprite.Enemy;

public class Level {
    

    
    private List<Enemy> enemies;
    
    private List<AbstractPlatform> platforms;
    
    private List<PowerUp> powerUps;
    
    
    public Level()
    {
        enemies = new ArrayList<Enemy>();
        platforms = new ArrayList<AbstractPlatform>();
        powerUps = new ArrayList<PowerUp>();
    }
    
    public String makeJsonString()
    {
        Gson gson = new Gson();
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
      /*  ArrayList<String> enemyJSONList = new ArrayList<String>();
        for(Enemy e: enemies)
        {
            
        }*/
        ArrayList<String> platformJsonList = new ArrayList<String>();
        for(AbstractPlatform p: platforms)
        {
            platformJsonList.add(p.makeJsonString());
        }
        map.put(AbstractPlatform.class.toString(), platformJsonList);
        
        return gson.toJson(map);
        
    }
    
    public void addContents(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<HashMap<String, ArrayList<String>>>(){}.getType();
        HashMap<String, ArrayList<String>> map = gson.fromJson(json, collectionType);
        if(map.keySet().contains(AbstractPlatform.class.toString()))
        {
            ArrayList<String> platformStrings = map.get(AbstractPlatform.class.toString());
            for(String s: platformStrings)
            {
                platforms.add(AbstractPlatform.makePlatformFromJson(s));
            }
        }
    }
    
    public void addPlatform(AbstractPlatform p)
    {
        platforms.add(p);
    }
    public List<AbstractPlatform> getPlatforms()
    {
        return platforms;
    }
    
    public void addEnemy(Enemy e)
    {
        enemies.add(e);
    }
    
    public void addPowerUp(PowerUp p)
    {
        powerUps.add(p);
    }
    
    public static void main(String[] args)
    {
        Level lev = new Level();
        BaseLoader loader = new BaseLoader(new BaseIO(lev.getClass()), Color.PINK);
        
        BufferedImage[] image = new BufferedImage[4];
        image[0] = loader.getImage("resources/block3.png");
        image[1] = loader.getImage("resources/block3.png");
        image[2] = loader.getImage("resources/block3.png");
        image[3] = loader.getImage("resources/block3.png");
        ArrayList<String> imageNames = new ArrayList<String>();
        imageNames.add("resources/block3.png");
        imageNames.add("resources/block3.png");
        imageNames.add("resources/block3.png");
        imageNames.add("resources/block3.png");
        BreakablePlatform b = new BreakablePlatform(image,5 ,6,imageNames,  null );
        BreakablePlatform c = new BreakablePlatform(image,7 ,6,imageNames,  null );
        lev.addPlatform(b);
        lev.addPlatform(c);
        String s = lev.makeJsonString();
        Level lev2 = new Level();
        
        lev2.addContents(s);
        List<AbstractPlatform> x = lev2.getPlatforms();
        for(AbstractPlatform a: x)
        {
            System.out.println(a.getX());
        }
        
    }
    
}

