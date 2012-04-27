package attributes.enemyattributes;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import attributes.interfaces.Updateable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;

//Change location to coordinate

@SuppressWarnings("serial")
public class SideToSideMovement extends TwoPartMovement implements Updateable, JsonableAttribute
{
    

    @editorConstructor(parameterNames = { "distance" , "duration"})
    public SideToSideMovement (double distance, int duration)
    {
        super(distance,duration);
        
    }
    

    protected void movementPart1 ()
    {
        myAttributeUser.setX(myAttributeUser.getX()-myDistance);
        

    }


    protected void movementPart2 ()
    {
       myAttributeUser.setX(myAttributeUser.getX()+myDistance);
    }
    
    public void modifySideToSideMovement(int distance, int duration){
        myDistance+=distance;
        myPartDuration+=duration;
    }


    @Override
    public String getName ()
    {
        return "SideToSideMovement";
    }


    public String toString ()
    {
        return "Attribute SideToSideMovement my distance is " + myDistance + " my time to turn is " + myPartDuration;
    }
    
    public Object clone()
    {
        return new SideToSideMovement(myDistance, myPartDuration);
    }
    
    //TODO fix json problem
    public String toJson()
    {
        Gson gson = new Gson();
        List<String> argList = new ArrayList<String>();
        argList.add(myDistance+"");
        argList.add(myPartDuration+"");
        
        return gson.toJson(argList);
    }
    
    public SideToSideMovement fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        List<String> argList = gson.fromJson(json, collectionType);
        return new SideToSideMovement(Double.parseDouble(argList.get(0)),Integer.parseInt((argList.get(1))));
    }
    
    private SideToSideMovement(){}
    public static AttributeFactory<SideToSideMovement> getFactory()
    {
        return new AttributeFactory<SideToSideMovement>(new SideToSideMovement());
    }
   

}
