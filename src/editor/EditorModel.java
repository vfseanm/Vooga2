package editor;


import java.awt.image.BufferedImage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import platforms.AbstractPlatform;
import powerUps.PowerUp;
import enemies.Behavior;

import sprite.Enemy;
import sprite.GameSprite;


public class EditorModel {
private ArrayList<GameSprite> sprites;
private List<Enemy> enemies;
private List<AbstractPlatform> platforms;
private List<PowerUp> powerUps;
private ArrayList<EnemyFramework> myEnemyFrameworks;
private ArrayList<BlankButton> blankButtons;
private SetGame myView;

public EditorModel(SetGame view)
{
    myView = view;
    blankButtons = new ArrayList<BlankButton>();
    myEnemyFrameworks = new ArrayList<EnemyFramework>();
    sprites = new ArrayList<GameSprite>();
    enemies = new ArrayList<Enemy>();
    platforms = new ArrayList<AbstractPlatform>();
    powerUps = new ArrayList<PowerUp>();
}

public ArrayList<GameSprite> getSprites()
{
    return sprites;
}

public void addSprite(GameSprite s)
{
    sprites.add(s);
}
public void clearSprites()
{
    sprites.clear();
}
public void addAllSprites(ArrayList<GameSprite> s)
{
    sprites.addAll(s);
}

public void addEnemy(BufferedImage image, String filePath, String name, ArrayList<Behavior> b)
{
    //EnemyFramework enemyFrame = new EnemyFramework(image, name, b);
   // myEnemyFrameworks.add(enemyFrame);
    //System.out.println("Added new Enemy Frame:" + enemyFrame);
    
    Button newButton = new Button(name, 10, 540, 60, 40, image, filePath, "enemy", b);
    myView.addButton(newButton);
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


}
