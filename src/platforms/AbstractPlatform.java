package platforms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sprite.AnimatedGameSprite;
import fighter.*;

@SuppressWarnings("serial")
public abstract class AbstractPlatform extends AnimatedGameSprite {
    
    Fighter myFighter;

	protected AbstractPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter) {
		super(im, x, y, images);
		myFighter = fighter;
	}
	
	
	public abstract void doBehavior(double speed, double distance);
	public abstract void update(long elapsedTime);
	//public abstract AbstractPlatform getNextState();
	public String makeJsonString()
    {
        Gson gson = new Gson();
        List<String> myList = new ArrayList<String>();
        myList.add(this.getType());
        myList.add(gson.toJson(getImageName()));
        myList.add(""+getX());
        myList.add(""+getY());
        return gson.toJson(myList);
        
    }
	

    public static AbstractPlatform makePlatformFromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> myList = gson.fromJson(json, collectionType);
        try
        {
            Class spriteClass = Class.forName(myList.get(0).substring(6));
            ArrayList<String> imageNameList = gson.fromJson(myList.get(1), collectionType);
            BaseLoader loader = new BaseLoader(new BaseIO(AbstractPlatform.class), Color.PINK);
            BufferedImage[] images = new BufferedImage[imageNameList.size()];
            for(int i= 0; i<imageNameList.size(); i++)
            {
                images[i] = loader.getImage(imageNameList.get(i));
            }
            double x = Double.valueOf(myList.get(2)).doubleValue();
            double y = Double.valueOf(myList.get(3)).doubleValue();
            Class partypes[] = new Class[5];
            partypes[0] = images.getClass();
            partypes[1] = double.class;
            partypes[2] = double.class;
            partypes[3] = imageNameList.getClass();
            partypes[4] = Fighter.class;
            Constructor ct  = spriteClass.getConstructor(partypes);
            Object[] argList = new Object[5];
            argList[0] = images;
            argList[1] = x;
            argList[2] = y;
            argList[3] = imageNameList;
            argList[4] = null;
            return (AbstractPlatform) ct.newInstance(argList);
            
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
        } catch (InstantiationException e)
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
    
    public static void main(String[] args)
    {

        BaseLoader loader = new BaseLoader(new BaseIO(AbstractPlatform.class), Color.PINK);
        BufferedImage[] image = new BufferedImage[4];
        image[0] = loader.getImage("resources/block3.png");
        image[1] = loader.getImage("resources/block3.png");
        image[2] = loader.getImage("resources/block3.png");
        image[3] = loader.getImage("resources/block3.png");
        ArrayList<String> imageNames = new ArrayList<String>();
        imageNames.add("resources/block3.png");
        imageNames.add("resources/block3.png");
        imageNames.add("resources/block3.png");
        imageNames.add("resources/block3.png");
        BreakablePlatform b = new BreakablePlatform(image,5 ,6,imageNames,  null );
        String s = b.makeJsonString();
        AbstractPlatform a = AbstractPlatform.makePlatformFromJson(s);
        System.out.println(a.getX());
        
    }
    
	
	

}
