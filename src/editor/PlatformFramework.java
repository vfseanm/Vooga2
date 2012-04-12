package editor;

import java.awt.image.BufferedImage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import platforms.AbstractPlatform;
import platforms.DecoratedPlatform;
import platforms.Platform;
import platforms.SimplePlatform;


import sprite.AnimatedGameSprite;

import attributes.Attribute;


public class PlatformFramework implements Framework {

    @SuppressWarnings("unused")
    private BufferedImage[] myImages;
    @SuppressWarnings("unused")
    private List<String> imageNames;
    private List<Class> myPlatformWrappers;

    public PlatformFramework(BufferedImage[] im, List<String> images, List<Class> platformWrappers) {
        myImages = im;
        imageNames = images;
        myPlatformWrappers = platformWrappers;
    }


    public AnimatedGameSprite getSprite(int x, int y) {
        System.out.println("framework- imageArray:" + myImages);
        SimplePlatform platform = new SimplePlatform(myImages, x, y - myImages[0].getHeight(), imageNames, null);
        DecoratedPlatform myPlatform = null;
        Object[] list = new Object[1];
        list[0] = platform;
        for(Class c: myPlatformWrappers)
        {
            System.out.println("class:" + c);
            System.out.println("lenght" + c.getConstructors().length);
            Constructor constructor=  c.getConstructors()[1];
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
            
        }
        
        
        return platform;
    }
}
