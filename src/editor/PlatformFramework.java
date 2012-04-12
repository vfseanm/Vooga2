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


<<<<<<< HEAD

=======
>>>>>>> 38a73278714665023fcfa4577e0ab230a149baa1
    public AnimatedGameSprite getSprite(int x, int y) {
        
        SimplePlatform platform = new SimplePlatform(myImages, x, y, imageNames, null);
        DecoratedPlatform myPlatform = null;
        Object[] list = new Object[1];
        list[0] = platform;
        for(Class c: myPlatformWrappers)
        {
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
            
        }
        
        
        return myPlatform;
    }
}
