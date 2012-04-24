package editor.exampleStuff;

import java.util.ArrayList;
import java.util.List;


import sprite.AnimatedGameSprite;

@SuppressWarnings("serial")
public class WildAndCrazyObject extends AnimatedGameSprite{
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
        Zone zone = myZone;
        List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        
        WildAndCrazyObject clone = new WildAndCrazyObject(this.getX(), this.getY(), imageNames);
        clone.setZone(zone);
        return clone;
        
        
    }
    
}
