package sidescrolling;

import java.io.Serializable;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.golden.gamedev.engine.BaseInput;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.ReflectionUtil;


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
     * 
     * @return the width of the game stored in the ConcreteSidescroller
     */
    public abstract int getGameWidth();
    
    /**
     * 
     * @return the height of the game stored in the ConcreteSidescroller
     */
    public abstract int getGameHeight();
    
    /**
     * Gives sidescroller a BaseInput which can be used later for things such as
     * checking if keys are pressed.
     * @param userInput - a BaseInput from the Game
     */
    public abstract void setUserInput(BaseInput userInput);
            
    public String toJson()
    {
        Gson gson = new Gson();

        List<String> paramList = new ArrayList<String>();

        if(!this.getClass().equals(ConcreteSidescroller.class))
        {
            List<String> classNames = new ArrayList<String>();
            for(@SuppressWarnings("rawtypes") Class c: ((DecoratedSidescroller) this).getClassesOfDecorators())
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


        List<String> paramList = gson.fromJson(json, collectionType);
        List<String> classList = gson.fromJson(paramList.get(0), collectionType);
        Sidescroller scroller = new ConcreteSidescroller();
        
        return (Sidescroller) ReflectionUtil.wrapObject(classList, scroller);
    }
}
