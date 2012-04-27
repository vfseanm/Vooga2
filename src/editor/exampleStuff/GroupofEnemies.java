package editor.exampleStuff;

import java.io.Serializable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sprite.AnimatedGameSprite;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.Framework;

import editor.input.inputTypes.InputType;
import enemies.Enemy;


@SuppressWarnings("serial")
public class GroupofEnemies implements InputType, Serializable {

    private ArrayList<AnimatedGameSprite> mySprites;

    public String getPrompt() {
        return "Click on the enemies that you'd like to add to the group";
    }
    
    public GroupofEnemies()
    {
        mySprites = new ArrayList<AnimatedGameSprite> ();
    }

    public ArrayList<AnimatedGameSprite> getEnemies() {
        
        return mySprites;
    }

    public void setXY(int x, int y) {
        return;
    }

    public void setEnemies(ArrayList<AnimatedGameSprite> sprites) {
        mySprites = sprites;
    }

    public void setRightClickedSprite(AnimatedGameSprite sprite) {
        return;
    }

    public void setLeftClickedSprite(AnimatedGameSprite sprite) {

      
        if (mySprites == null)
            mySprites = new ArrayList<AnimatedGameSprite>();
        mySprites.add(sprite);
    }

    public void setLeftClickedFramework(Framework f) {
        return;
    }

    public void setRightClickedFramework(Framework f) {
        return;
    }

    public String toJson()
    {
        Gson gson = new Gson();
        Map<String, String> spriteMap = new HashMap<String,String>();
        for(AnimatedGameSprite sprite: mySprites)
        {
            if(sprite!=null)
            {
                spriteMap.put(sprite.getClass().toString(), sprite.toJson());
            }
        }
        return gson.toJson(spriteMap);
        
    }
    
    public Object clone()
    {
        GroupofEnemies group = new GroupofEnemies();
        ArrayList<AnimatedGameSprite> sprites = new ArrayList<AnimatedGameSprite>();
        
        for(AnimatedGameSprite s: mySprites )
        {
            if(s!=null)
            {
                sprites.add((AnimatedGameSprite) s.clone());
            }
        }
        group.setEnemies(sprites);
        return group;
    }
    
    public static GroupofEnemies fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Map<String, String>>()
        {}.getType();
        GroupofEnemies group = new GroupofEnemies();
        Map<String,String> spriteMap = gson.fromJson(json, collectionType);
        ArrayList<AnimatedGameSprite> enemyList = new ArrayList<AnimatedGameSprite>();
        for(String spriteString: spriteMap.keySet())
        {
            enemyList.add((AnimatedGameSprite) Enemy.getFactory().parseFromJson(spriteMap.get(spriteString)));
        }
        group.setEnemies(enemyList);
        return group;
        
    }

}
