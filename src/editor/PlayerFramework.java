package editor;

import java.awt.image.BufferedImage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import carryables.Carryable;

import enemies.Enemy;

import sprite.AnimatedGameSprite;

import attributes.Attribute;


public class PlayerFramework implements Framework {

    protected ArrayList<Attribute> attributes;
    @SuppressWarnings("unused")
    private BufferedImage[] myImages;
    @SuppressWarnings("unused")
    private ArrayList<String> imageNames;
    private List<List<Object>> myAttributes;

    public PlayerFramework(BufferedImage[] im, ArrayList<String> images, List<List<Object>> attributes, List<Carryable> carryables) {
        myImages = im;
       // System.out.println("attributes:" + attributes);
        imageNames = images;
        myAttributes = attributes;
    }

    public void addBehavior(Attribute b) {
        attributes.add(b);
    }

 
    public AnimatedGameSprite getSprite(int x, int y) {
        Enemy e = new Enemy(myImages, x,
                y - myImages[0].getHeight(),
                imageNames);
        for(List<Object> list: myAttributes)
        {
            Constructor c = (Constructor) list.get(0);
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
            }
            e.addAttribute(attribute);
        }  
        return e;
    }

    @Override
    public void updateSprites(List<Object> parameters) {
        // TODO Auto-generated method stub
        
    }

   
}
