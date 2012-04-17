package editor.frameworks;

import java.awt.image.BufferedImage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import editor.AttributeCreator;

import bonusobjects.PowerUp;





import sprite.AnimatedGameSprite;

import attributes.Attribute;


public class PowerupFramework implements Framework {

    private BufferedImage[] myImages;
    private ArrayList<String> imageNames;
    private List<AttributeCreator> myAttributes;
    private List<AttributeCreator> myAttributesToGive;
    private List<PowerUp> mySprites;
    private String myGroup;

    public PowerupFramework(BufferedImage[] im, ArrayList<String> images, List<AttributeCreator> attributes, List<AttributeCreator> attributesToGive, String group) {
        myGroup = group;
        myImages = im;
        imageNames = images;
        myAttributes = attributes;
        myAttributesToGive = attributesToGive;
        mySprites = new ArrayList<PowerUp>();
    }

 /*   public void addBehavior(Attribute b) {
        attributes.add(b);
    }

*/
    public AnimatedGameSprite getSprite(int x, int y) {
        List<Attribute> attributes = new ArrayList<Attribute>();
        List<Attribute> attributesToGive = new ArrayList<Attribute>();
        attributes = new ArrayList<Attribute>();
        for(AttributeCreator a: myAttributes)
        {
            Attribute attribute = a.createAttribute();
           
            attributes.add(attribute);
        }  
        
        for(AttributeCreator a: myAttributesToGive)
        {

            Attribute attribute = a.createAttribute();
            attributesToGive.add(attribute);
        }  
        
        PowerUp powerUp = new PowerUp(myImages, x,
                y - myImages[0].getHeight(),
                imageNames);
        
        for(Attribute a: attributes)
        {
            powerUp.addAttribute(a);
        }
        for(Attribute a: attributesToGive)
        {
            powerUp.addAttributeToOffer(a);
        }
        
        powerUp.setGroup(myGroup);
        mySprites.add(powerUp);
        return powerUp;
    }

    @Override
    public void updateSprites(List<Object> parameters) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getType() {
        return "Power-Up";
    }
    public boolean containsSprite(AnimatedGameSprite s)
    {
        return mySprites.contains(s);
    }
    public void removeSprite(AnimatedGameSprite s)
    {
        mySprites.remove(s);
    }


}
