package editor;

import java.awt.image.BufferedImage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import powerups.PowerUp;



import enemies.Enemy;

import sprite.AnimatedGameSprite;

import attributes.Attribute;


public class PowerupFramework implements Framework {

    @SuppressWarnings("unused")
    private BufferedImage[] myImages;
    @SuppressWarnings("unused")
    private ArrayList<String> imageNames;
    private List<AttributeCreator> myAttributes;
    private List<AttributeCreator> myAttributesToGive;
    private List<Attribute> attributes;
    private List<Attribute> attributesToGive;

    public PowerupFramework(BufferedImage[] im, ArrayList<String> images, List<AttributeCreator> attributes, List<AttributeCreator> attributesToGive) {
        myImages = im;
        imageNames = images;
        myAttributes = attributes;
        myAttributesToGive = attributesToGive;
    }

    public void addBehavior(Attribute b) {
        attributes.add(b);
    }


    public AnimatedGameSprite getSprite(int x, int y) {
        attributes = new ArrayList<Attribute>();
        for(AttributeCreator a: myAttributes)
        {
            Attribute attribute = a.createAttribute();
            /*Constructor c = (Constructor) list.get(0);
            Object[] parameterList = (Object[]) list.get(1);
            Attribute attribute = null;
            try {
                attribute = (Attribute) c.newInstance(parameterList);
            } catch (IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }*/
            attributes.add(attribute);
        }  
        
        for(AttributeCreator a: myAttributesToGive)
        {
            /*Constructor c = (Constructor) list.get(0);
            Object[] parameterList = (Object[]) list.get(1);
            Attribute attribute = null;
            try {
                attribute = (Attribute) c.newInstance(parameterList);
            } catch (IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }*/
            Attribute attribute = a.createAttribute();
            attributesToGive.add(attribute);
        }  
        
        PowerUp powerUp = new PowerUp(myImages, x,
                y - myImages[0].getHeight(),
                imageNames, attributes, attributesToGive);
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

}
