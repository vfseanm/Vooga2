package sidescrolling;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sprite.AnimatedGameSprite;


/**
 * Sidescroller is an interface that allows one to implement objects which allow for sidescrolling.
 * Each sidescroller must implement a method that updates the screen when sidescrolling
 * occurs, gets a list of sprite groups, and gets the fighter.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public abstract class Sidescroller implements Serializable  {
            
    transient protected ResourceBundle mySidescrollerResources = ResourceBundle
            .getBundle("sidescrolling.SidescrollerResourceBundle");
    
    /**
     * Updates the sidescroller.
     * @param elapsedTime
     */
    public abstract void update(long elapsedTime);
    
    /**
     * @return the list of Sprites in the ConcreteSidescroller
     */
    public abstract ArrayList<AnimatedGameSprite> getSprites();
    
    /**
     * 
     * @return the width of the game stored in the ConcreteSidescroller
     */
    public abstract int getGameWidth();
    
    /**
     * 
     * @return the height of the game stored in the ConcreteSidescroller
     */
    public abstract int getGameHeight();
    
    public abstract void setSprites(ArrayList<AnimatedGameSprite> sprites);
    
    public String toJson()
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>()
        {}.getType();
        List<String> paramList = new ArrayList<String>();
        paramList.add(this.getGameHeight() + "");
        paramList.add(this.getGameWidth() + "");
        if(!this.getClass().equals(ConcreteSidescroller.class))
        {
            List<String> classNames = new ArrayList<String>();
            for(Class c: ((DecoratedSidescroller) this).getClassesOfDecorators())
            {
                classNames.add(c.toString());
            }
            paramList.add(gson.toJson(classNames));
        }
        else
        {
            paramList.add(gson.toJson(new ArrayList<String>()));
        }
        return gson.toJson(paramList);
        
    }
    
    public static Sidescroller fromJson(String json){
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>()
        {}.getType();
        Type collectionType2 = new TypeToken<List<Class>>()
        {}.getType();

        List<String> paramList = gson.fromJson(json, collectionType);
        int x = Integer.parseInt(paramList.get(0));
        int y = Integer.parseInt(paramList.get(1));
        List<String> classList = gson.fromJson(paramList.get(2), collectionType);
        Sidescroller scroller = new ConcreteSidescroller(x, y);
        Object[] list = new Object[1];
        list[0] = scroller;
        for(String wrappingClass: classList)
        {
                
            
                try {
                    Class attributeClass = Class.forName(wrappingClass.substring(6));
                    Constructor constructor=  attributeClass.getConstructors()[0];
                    scroller = (DecoratedSidescroller) constructor.newInstance(list);
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
                } catch (ClassNotFoundException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                list[0] = scroller;
            
        
        }
        return scroller;
    }
}
