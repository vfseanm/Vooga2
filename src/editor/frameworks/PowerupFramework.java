package editor.frameworks;

import java.awt.image.BufferedImage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import editor.AttributeCreator;
import enemies.Enemy;

import bonusobjects.PowerUp;





import sprite.AnimatedGameSprite;

import attributes.Attribute;


public class PowerupFramework extends Framework {

    private List<AttributeCreator> myAttributes;
    private List<AttributeCreator> myAttributesToGive;
    private String myGroup;

    public PowerupFramework(BufferedImage[] im, ArrayList<String> images, List<AttributeCreator> attributes, List<AttributeCreator> attributesToGive, String group) {
        myGroup = group;
        myImages = im;
        imageNames = images;
        myAttributes = attributes;
        myAttributesToGive = attributesToGive;
        mySprites = new ArrayList<AnimatedGameSprite>();
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
    public String getType() {
        return "Power-Up";
    }

    @Override
    public void updateSprites(AnimatedGameSprite sprite)
    {
        // TODO Auto-generated method stub
        
    }



}
