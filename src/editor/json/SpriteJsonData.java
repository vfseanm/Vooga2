package editor.json;

import java.util.List;

import sprite.AnimatedGameSprite;

public class SpriteJsonData {
    
    private double myX;
    private double myY;
    private String myGroup;
    private List<String> myImageNames;
    private String myAdditionalInformation;
    
    public SpriteJsonData(AnimatedGameSprite s, String additionalInformation)
    {
       myX = s.getX();
       myY = s.getY();
       myGroup = s.getGroup();
       myImageNames = s.getImageNames();
       myAdditionalInformation = additionalInformation;
    }
    
    
    public double getX()
    {
        return myX;
    }
    
    public double getY()
    {
        return myY;
    }
    public List<String> getImageNames()
    {
        return myImageNames;
    }
    
    public String getAdditionalInformation()
    {
        return myAdditionalInformation;
    }
    
    public String getGroup()
    {
        return myGroup;
    }
    
    public String toString()
    {
        return myX+ " " +myY+ " "+ myImageNames.toString()+ " "+ myAdditionalInformation;
    }

}
