package editor.frameworks;

import java.awt.Color;


import java.awt.image.BufferedImage;
import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import sprite.AnimatedGameSprite;

@SuppressWarnings("serial")
public class Framework implements Serializable {
    protected List<AnimatedGameSprite> mySprites;
    //transient protected BufferedImage[] myImages;
    //protected List<String> imageNames;
    private AnimatedGameSprite prototypeSprite;
    private String myType;
    private String myName;
    
    public Framework(String name, String type, AnimatedGameSprite s)
    {
        myName = name;
        prototypeSprite = s;
        myType = type;
        mySprites = new ArrayList<AnimatedGameSprite>();
    }
   
    public AnimatedGameSprite getPotentialSprite(int x, int y)
    {
        AnimatedGameSprite s = (AnimatedGameSprite) prototypeSprite.clone();
        s.setX(x);
        s.setY(y-s.getHeight());
        return s;
    }
    public void createSprite(int x, int y)
    {
        AnimatedGameSprite s = (AnimatedGameSprite) prototypeSprite.clone();
        s.setX(x);
        s.setY(y-s.getHeight());
        mySprites.add(s);
    }
    public void addSprite(AnimatedGameSprite s)
    {
        mySprites.add( s);
    }
    public String getName()
    {
        return myName;
    }
    
    //public ArrayList<AnimatedGameSprite> getSprites();
    public void updateSprites(AnimatedGameSprite sprite)
    {
        List<AnimatedGameSprite> newSprites = new ArrayList<AnimatedGameSprite>();
        prototypeSprite = sprite;
        System.out.println(prototypeSprite.getImageNames());
        for(AnimatedGameSprite s: mySprites)
        {
            
            AnimatedGameSprite toAdd =(AnimatedGameSprite) prototypeSprite.clone();
            toAdd.setX(s.getX());
            toAdd.setY(s.getY());
            newSprites.add(toAdd);        
            
        }
        mySprites = newSprites;
    }
    
    public String getType()
    {
        return myType;
    }

    
    public void moveHorizontally(double x)
    {
        for (Sprite s : mySprites)
        {
            s.setX(s.getX() + x);
        }
    }
    
    public void moveVertically(double y)
    {
        for(Sprite s: mySprites)
        {
            s.setY(s.getY()+y);
        }
    }
    
    public List<AnimatedGameSprite> getAllSprites()
    {
        return Collections.unmodifiableList(mySprites);
    }
    
    public boolean containsSprite(AnimatedGameSprite s)
    {
        return mySprites.contains(s);
    }
    public void removeSprite(AnimatedGameSprite s)
    {
        mySprites.remove(s);
    }
    
    
    public List<AnimatedGameSprite> getSprites()
    {
        return mySprites;
    }
    
    public void updateImages()
    {
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
        for(AnimatedGameSprite s: mySprites)
        {
            
            BufferedImage[] images = new BufferedImage[s.getImageNames().size()];
            for(int i=0; i<images.length; i++)
            {
                //System.out.println("image names: "+s.getImageNames());
                images[i] = loader.getImage(s.getImageNames().get(i));
            }
            s.setImages(images);
         }
        
    /*    myImages = new BufferedImage[imageNames.size()];
        for(int i=0; i<myImages.length; i++)
        {
            //System.out.println("image names: "+s.getImageNames());
            myImages[i] = loader.getImage(imageNames.get(i));
        }
        */
        
    }
    
    public AnimatedGameSprite getPrototype()
    {
        return prototypeSprite;
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        
        List<String> list = new ArrayList<String>();
        list.add(prototypeSprite.getClass().toString());
        System.out.println("proto:   "+prototypeSprite.toJson());
        list.add(prototypeSprite.toJson());
        
        List<String> spriteList = new ArrayList<String>();
        for(AnimatedGameSprite s: mySprites)
        {
            List<Double> position = new ArrayList<Double>();
            position.add(s.getX());
            position.add(s.getY());
            spriteList.add(gson.toJson(position));
        }
        list.add(gson.toJson(spriteList));
        
        return gson.toJson(list);
        
        
    }
    
    public static Framework fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        Type collectionType2 = new TypeToken<List<Double>>(){}.getType();
        List<String> list = gson.fromJson(json, collectionType);
       // System.out.println(list);
        try
        {
            Class prototypeClass = Class.forName(list.get(0).substring(6));
            String prototypeJson = list.get(1);
            List<String> instanceList = gson.fromJson(list.get(2), collectionType);
            Class typeList[] = new Class[1];
            typeList[0] = String.class;
            Method method = prototypeClass.getMethod("fromJson", typeList);
            System.out.println(method);
            AnimatedGameSprite prototype = (AnimatedGameSprite) method.invoke(null,prototypeJson);
            Framework framework = new Framework("testName", "blah", prototype);
            for(String s: instanceList)
            {
                List<Double> coordinates = gson.fromJson(s, collectionType2);
                framework.createSprite( (int) ((double)coordinates.get(0)), (int)((double)coordinates.get(1)));
            }
            return framework;
            
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        } catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        
        return null;
    }
   
    
}
