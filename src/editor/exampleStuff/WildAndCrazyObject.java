package editor.exampleStuff;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;

import editor.json.JsonableSprite;
import editor.json.SpriteFactory;
import editor.json.SpriteJsonData;


import sprite.AnimatedGameSprite;


@SuppressWarnings("serial")
public class WildAndCrazyObject extends AnimatedGameSprite implements JsonableSprite, Serializable{
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
    
    public WildAndCrazyObject fromJson(String json)
    {
        Gson gson = new Gson();
        SpriteJsonData spriteData = gson.fromJson(json, SpriteJsonData.class);
        WildAndCrazyObject sprite = new WildAndCrazyObject(spriteData.getX(), spriteData.getY(), spriteData.getImageNames());
        sprite.setGroup(spriteData.getGroup());
        Zone zone =  Zone.fromJson(spriteData.getAdditionalInformation());
        sprite.setZone(zone);
        return sprite;
        
    }
    
    private WildAndCrazyObject(){};
    public static SpriteFactory<WildAndCrazyObject> getFactory()
    {
        return new SpriteFactory<WildAndCrazyObject>(new WildAndCrazyObject());
    }
    
}
