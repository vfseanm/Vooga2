package editor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import sprite.AnimatedGameSprite;

import editor.input.Zone;
import enemies.Enemy;

@SuppressWarnings("serial")
public class WildAndCrazyObject extends AnimatedGameSprite{
    private Zone myZone;
    private HashSet<Enemy> myEnemies;
    private ArrayList<AnimatedGameSprite> myOtherThings;
    private Object myNonGameObject;
    
    public WildAndCrazyObject(double x, double y, List<String> images)
    {
        super(x, y, images);
    }
    public void setZone(Zone zone)
    {
        myZone = zone;
        setGroup("WILDANDCRAZY");
    }
    
    public Object clone ()
    {
        Zone zone = myZone; //   CHANGE THIS
        
       
        
        List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        
        WildAndCrazyObject clone = new WildAndCrazyObject(this.getX(), this.getY(), imageNames);
        clone.setZone(zone);
        return clone;
        
        
    }
    
}
