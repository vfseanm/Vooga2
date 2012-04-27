package editor;

import java.awt.Color;


import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import platforms.platformtypes.BreakablePlatform;
import platforms.platformtypes.RotatingPlatform;
import platforms.platformtypes.SideToSidePlatform;
import platforms.platformtypes.SimplePlatform;
import platforms.platformtypes.UpDownPlatform;

import bonusobjects.BonusObject;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.exampleStuff.WildAndCrazyObject;
import editor.json.SpriteFactory;
import enemies.Enemy;

import sprite.AnimatedGameSprite;

@SuppressWarnings({ "serial", "rawtypes" })
public class Framework implements Serializable {
    protected List<AnimatedGameSprite> mySprites;

    private AnimatedGameSprite prototypeSprite;
    private String myType;
    private String myName;
    private static List<SpriteFactory> factories ;
    static
    {
        factories = new ArrayList<SpriteFactory>();
        factories.add( Enemy.getFactory());
        factories.add( BreakablePlatform.getFactory());
        factories.add(SimplePlatform.getFactory());
        factories.add(SideToSidePlatform.getFactory());
        factories.add(UpDownPlatform.getFactory());
        factories.add(RotatingPlatform.getFactory());
        factories.add(BonusObject.getFactory());
        factories.add(WildAndCrazyObject.getFactory());
    }
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
        s.setY(y);
        return s;
    }

    public void createSprite(int x, int y)
    {
        AnimatedGameSprite s = (AnimatedGameSprite) prototypeSprite.clone();
        s.setX(x);
        s.setY(y);
        mySprites.add(s);
    }

    public void addSprite(AnimatedGameSprite s)
    {
        mySprites.add(s);
    }

    public String getName()
    {
        return myName;
    }

    // public ArrayList<AnimatedGameSprite> getSprites();
    public void updateSprites(AnimatedGameSprite sprite)
    {
        List<AnimatedGameSprite> newSprites = new ArrayList<AnimatedGameSprite>();
        prototypeSprite = sprite;
        for (AnimatedGameSprite s : mySprites)
        {

            AnimatedGameSprite toAdd = (AnimatedGameSprite) prototypeSprite
                    .clone();
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
        for (Sprite s : mySprites)
        {
            s.setY(s.getY() + y);
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
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()),
                Color.PINK);
        for (AnimatedGameSprite s : mySprites)
        {

            BufferedImage[] images = new BufferedImage[s.getImageNames().size()];
            for (int i = 0; i < images.length; i++)
            {
                images[i] = loader.getImage(s.getImageNames().get(i));
            }
            s.setImages(images);
        }

    }

    public AnimatedGameSprite getPrototype()
    {
        return prototypeSprite;
    }

    @SuppressWarnings("unused")
	public String toJson()
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>() {
        }.getType();

        List<String> list = new ArrayList<String>();
        list.add(myType);
        list.add(myName);
        list.add(prototypeSprite.getClass().toString());
        list.add(prototypeSprite.toJson());

        List<String> spriteList = new ArrayList<String>();
        for (AnimatedGameSprite s : mySprites)
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
        Type collectionType = new TypeToken<List<String>>() {
        }.getType();
        Type collectionType2 = new TypeToken<List<Double>>() {
        }.getType();
        List<String> list = gson.fromJson(json, collectionType);
        String type = list.get(0);
        String name = list.get(1);
        String prototypeClassName = list.get(2);
        String prototypeJson = list.get(3);
        List<String> instanceList = gson.fromJson(list.get(4), collectionType);

        
        
        
        AnimatedGameSprite prototype = null;
        for(SpriteFactory factory: factories)
        {
            if(factory.isThisKindOfSprite(prototypeClassName))
            {
                prototype = factory.parseFromJson(prototypeJson);
            }
        }
        Framework framework = new Framework(name, type, prototype);

        for (String s : instanceList)
        {
            List<Double> coordinates = gson.fromJson(s, collectionType2);
            framework.createSprite((int) ((double) coordinates.get(0)),
                    (int) ((double) coordinates.get(1)));
        }
        return framework;
    }   

}
