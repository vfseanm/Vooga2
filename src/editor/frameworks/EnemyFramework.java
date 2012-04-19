package editor.frameworks;

import java.awt.image.BufferedImage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import editor.AttributeCreator;
import enemies.Enemy;

import sprite.AnimatedGameSprite;

import attributes.Attribute;


public class EnemyFramework extends Framework {

    protected ArrayList<Attribute> attributes;

    private List<AttributeCreator> myAttributes;
    //private List<Attribute> allAttributes;
    private String myGroup;

    public EnemyFramework(BufferedImage[] im, ArrayList<String> images, List<AttributeCreator> attributes, String group) {
        myGroup = group;
        myImages = im;
        imageNames = images;
        myAttributes = attributes;
        mySprites = new ArrayList<AnimatedGameSprite>();
        //allAttributes = new ArrayList<Attribute>();
    }


    public BufferedImage[] getImages()
    {
        return myImages;
    }

    public AnimatedGameSprite getSprite(int x, int y) {
        Enemy e = new Enemy(x,
                y - myImages[0].getHeight(),
                imageNames);
        for(AttributeCreator a: myAttributes)
        {
    
            Attribute attribute = null;
            attribute = a.createAttribute();
            
           
            //allAttributes.add(attribute);
            e.addAttribute(attribute);
            e.setGroup(myGroup);
        }  
        mySprites.add(e);
        return e;
    }

    public void updateSprites(AnimatedGameSprite enemy) {
        Enemy e = (Enemy) enemy;
        myImages = e.getImages(); //(BufferedImage[]) parameters.get(0);
        imageNames = e.getImageNames(); //(ArrayList<String>) parameters.get(1);
        myAttributes = e.getAttributes(); //(List<AttributeCreator>) parameters.get(2);
        for(AnimatedGameSprite e: mySprites)
        {
            e.setImages(myImages);
            e.setImageNames(imageNames);
            ((Enemy)e).clearAttributes();
            for(AttributeCreator a: myAttributes)
            {
               
                Attribute attribute = a.createAttribute();
                ((Enemy)e).addAttribute(attribute);
                
            }  
        }
        
    }
    
    public String getType()
    {
        return "enemy";
    }
    
    public List<AttributeCreator> getAttributeCreators()
    {
        return myAttributes;
    }
    


}
