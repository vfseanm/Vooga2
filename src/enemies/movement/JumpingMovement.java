package enemies.movement;

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


// go do isActive and use that
// turnaround
@SuppressWarnings("serial")
public class JumpingMovement extends Attribute implements Updateable, JsonableAttribute
{
    private int myDistance;
    private int myTime;
    private int time;


    @editorConstructor(parameterNames = { "distance", "time" })
    public JumpingMovement (int distance, int delay)
    {

        super(distance, delay);
        myDistance = distance;
        myTime = delay;
        time=0;
        
        
    }

    public void modifyJumpingMovement (int distance, int time)
    {
        myDistance += distance;
        myTime += time;
    }

    public void setActivity(boolean active){
    	myGameCharacter.allowAttribute("Gravity", true);
    	isActive=active;
    }
    
    public void update (long elapsedTime)
    {
        
        if (isActive)
        {
            if (time <= myTime)
            {
                
                myGameCharacter.setY(myGameCharacter.getY() - myDistance);
                myGameCharacter.allowAttribute("Gravity", false);
            }
            else
            {
                
                myGameCharacter.restoreOriginalAttribute("Gravity");
            }
        }
        
        time++;

    }
    //Repeated Code Andrew Help
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
    
    public String toJson()
    {
        Gson gson = new Gson();
        List<Integer> argList = new ArrayList<Integer>();
        argList.add(myDistance);
        argList.add(myTime);
        return gson.toJson(argList);
    }
    
    public JumpingMovement fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Integer>>(){}.getType();
        List<Integer> argList = gson.fromJson(json, collectionType);
        return new JumpingMovement(argList.get(0), argList.get(1));
    }
    
    private JumpingMovement(){}
    
    public static AttributeFactory<JumpingMovement> getFactory()
    {
        return new AttributeFactory<JumpingMovement>(new JumpingMovement());
    }
   

}
