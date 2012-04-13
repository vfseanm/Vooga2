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
    private List<List<Object>> myAttributes;
    private List<Attribute> allAttributes;
    private ArrayList<Enemy> mySprites;

    public EnemyFramework(BufferedImage[] im, ArrayList<String> images, List<List<Object>> attributes) {
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
            allAttributes.add(attribute);
            e.addAttribute(attribute);
        }  
        mySprites.add(e);
        return e;
    }

    public void updateSprites(List<Object> parameters) {
        myImages = (BufferedImage[]) parameters.get(0);
        imageNames = (ArrayList<String>) parameters.get(1);
        myAttributes = (List<List<Object>>) parameters.get(2);
        for(Enemy e: mySprites)
        {
            e.setImages(myImages);
            e.setImageNames(imageNames);
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
                e.clearAttributes();
                e.addAttribute(attribute);
            }  
        }
        
    }

}
