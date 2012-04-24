package editor.exampleStuff;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;


import sprite.AnimatedGameSprite;

import editor.Reflection;
import editor.json.Jsonable;
import editor.json.SpriteJsonData;
import enemies.Enemy;

@SuppressWarnings("serial")
public class WildAndCrazyObject extends AnimatedGameSprite implements Jsonable{
    private Zone myZone;
    
    public WildAndCrazyObject(double x, double y, List<String> images)
    {
        super(x, y, images);
        setGroup("WILDANDCRAZY");
    }
    public void setZone(Zone zone)
    {
        myZone = zone;
    }
    
    public Object clone ()
    {
        Zone zone = (Zone) myZone.clone();
        List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        
        WildAndCrazyObject clone = new WildAndCrazyObject(this.getX(), this.getY(), imageNames);
        clone.setZone(zone);
        return clone;
        
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        String additionalInformation = myZone.toJson();
        SpriteJsonData spriteData = new SpriteJsonData(this, additionalInformation);
        return gson.toJson(spriteData);
    }
    
    public static WildAndCrazyObject fromJson(String json)
    {
        Gson gson = new Gson();
        SpriteJsonData spriteData = gson.fromJson(json, SpriteJsonData.class);
        WildAndCrazyObject sprite = new WildAndCrazyObject(spriteData.getX(), spriteData.getY(), spriteData.getImageNames());
        sprite.setGroup(spriteData.getGroup());
        Zone zone =  Zone.fromJson(spriteData.getAdditionalInformation());
        sprite.setZone(zone);
        return sprite;
        
    }
    
}
