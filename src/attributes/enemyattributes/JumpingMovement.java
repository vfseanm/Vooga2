package attributes.enemyattributes;

import java.lang.reflect.Type;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;
import attributes.Attribute;
import attributes.interfaces.Updateable;



@SuppressWarnings("serial")
public class JumpingMovement extends Attribute implements Updateable, JsonableAttribute
{
    private double myDistance;
    private int myTime;
    private int time;


    @editorConstructor(parameterNames = { "distance", "time" })
    public JumpingMovement (double distance, int delay)
    {

        super(distance, delay);
        myDistance = distance;
        myTime = delay;
        time=0;
        
        
    }

    public void modifyJumpingMovement (double distance, int time)
    {
        myDistance += distance;
        myTime += time;
    }

    public void setActivity(boolean active){
    	myAttributeUser.allowAttribute("Gravity", true);
    	isActive=active;
    }
    
    public void update (long elapsedTime)
    {
        
        if (isActive)
        {
            if (time <= myTime)
            {
                
                myAttributeUser.setY(myAttributeUser.getY() - myDistance);
                myAttributeUser.allowAttribute("Gravity", false);
            }
            else
            {
                
                myAttributeUser.restoreOriginalAttribute("Gravity");
            }
        }
        
        time++;

    }
    
    public void invert(){
        myDistance=-myDistance;
    }

    @Override
    public String getName ()
    {
        return "JumpingMovement";
    }


    public String toString ()
    {
        return "Attribute JumpingMovement my jump distance is " + myDistance +
               " my jump time is " + myTime+" activity is " +isActive;

    }
    
    public Object clone()
    {
        return new JumpingMovement(myDistance, myTime);
    }
    //TODO fix this Json problem
    public String toJson()
    {
        Gson gson = new Gson();
        List<String> argList = new ArrayList<String>();
        argList.add(myDistance+"");
        argList.add(myTime+"");
        return gson.toJson(argList);
    }
    
    public JumpingMovement fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        List<String> argList = gson.fromJson(json, collectionType);
        return new JumpingMovement(Double.parseDouble(argList.get(0)), Integer.parseInt(argList.get(1)));
    }
    
    private JumpingMovement(){}
    
    public static AttributeFactory<JumpingMovement> getFactory()
    {
        return new AttributeFactory<JumpingMovement>(new JumpingMovement());
    }
   

}
