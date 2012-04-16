package editor;

import java.awt.image.BufferedImage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import enemies.Enemy;

import sprite.AnimatedGameSprite;

import attributes.Attribute;


public class EnemyFramework implements Framework {

    protected ArrayList<Attribute> attributes;
    private BufferedImage[] myImages;
    private ArrayList<String> imageNames;
    private List<AttributeCreator> myAttributes;
    private List<Attribute> allAttributes;
    private ArrayList<Enemy> mySprites;
    private String myGroup;

    public EnemyFramework(BufferedImage[] im, ArrayList<String> images, List<AttributeCreator> attributes, String group) {
        myGroup = group;
        myImages = im;
        imageNames = images;
        myAttributes = attributes;
        mySprites = new ArrayList<Enemy>();
        allAttributes = new ArrayList<Attribute>();
    }

    public void addBehavior(Attribute b) {
        attributes.add(b);
    }
    public BufferedImage[] getImages()
    {
        return myImages;
    }
    public List<Attribute> getAttributes()
    {
        return allAttributes;
    }
 
    public AnimatedGameSprite getSprite(int x, int y) {
        Enemy e = new Enemy(myImages, x,
                y - myImages[0].getHeight(),
                imageNames);
        for(AttributeCreator a: myAttributes)
        {
    
            Attribute attribute = null;
            attribute = a.createAttribute();
            
           
            allAttributes.add(attribute);
            e.addAttribute(attribute);
            e.setGroup(myGroup);
        }  
        mySprites.add(e);
        return e;
    }

    public void updateSprites(List<Object> parameters) {
        myImages = (BufferedImage[]) parameters.get(0);
        imageNames = (ArrayList<String>) parameters.get(1);
        myAttributes = (List<AttributeCreator>) parameters.get(2);
        for(Enemy e: mySprites)
        {
            e.setImages(myImages);
            e.setImageNames(imageNames);
            for(AttributeCreator a: myAttributes)
            {
               
                Attribute attribute = a.createAttribute();
                e.clearAttributes();
                e.addAttribute(attribute);
                
            }  
        }
        
    }
    
    public String getType()
    {
        return "enemy";
    }

}
