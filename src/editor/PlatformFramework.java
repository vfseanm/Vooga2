package editor;

import java.awt.image.BufferedImage;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import enemies.Enemy;

import platforms.platformtypes.*;


import sprite.AnimatedGameSprite;

import attributes.Attribute;


public class PlatformFramework implements Framework {

    @SuppressWarnings("unused")
    private BufferedImage[] myImages;
    @SuppressWarnings("unused")
    private List<String> imageNames;
    private List<Class> myPlatformWrappers;
    private String myGroup;

    private ArrayList<AbstractPlatform> mySprites;

    public PlatformFramework(BufferedImage[] im, List<String> images, List<Class> platformWrappers, String group) {
        System.out.println("slkdfjsdlkj");
        myGroup = group;
        myImages = im;
        imageNames = images;
        myPlatformWrappers = platformWrappers;
        mySprites = new ArrayList<AbstractPlatform>();
    }



    public AnimatedGameSprite getSprite(int x, int y) {
        //System.out.println("framework- imageArray:" + myImages);
        //System.out.println("image names: "+imageNames);
        
        SimplePlatform platform = new SimplePlatform(myImages, x, y - myImages[0].getHeight(), imageNames);
        AbstractPlatform myPlatform = platform;
        Object[] list = new Object[1];
        list[0] = platform;
        for(Class c: myPlatformWrappers)
        {
            System.out.println("class:" + c);
            System.out.println("length" + c.getConstructors().length);
            Constructor constructor=  c.getConstructors()[0];
            try {
                myPlatform = (DecoratedPlatform) constructor.newInstance(list);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            list[0] = myPlatform;
            myPlatform.setGroup(myGroup);
            
        }
        mySprites.add(myPlatform);
        
        return myPlatform;
    }

    

    @Override
    public void updateSprites(List<Object> parameters) {
        myImages = (BufferedImage[]) parameters.get(0);
        imageNames = (ArrayList<String>) parameters.get(1);
        myPlatformWrappers = (List<Class>) parameters.get(2);
        
        
    }
    
    public String getType()
    {
        return "platform";
    }
}
