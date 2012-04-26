package enemies.movement;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;


// Idea master config file to control attribute exlusivity
// flying


@SuppressWarnings("serial")
public class UpDownMovement extends TwoPartMovement implements JsonableAttribute
{

    @editorConstructor(parameterNames = { "distance" , "duration"})
    public UpDownMovement (int distance, int duration)
    {
        super(distance,duration);
        
    }


    public void modifyUpDownMovement (int distance, int duration)
    {
        myDistance += distance;
        myPartDuration += duration;
    }


    protected void movementPart1 ()
    {
        myGameCharacter.setY( myGameCharacter.getY() + myDistance);
        

    }
 

    protected void movementPart2 ()
    {
        myGameCharacter.setY( myGameCharacter.getY() - myDistance);
        
    }


    @Override
    public String getName ()
    {
        return "UpDownMovement";
    }


    public String toString ()
    {
        return "Attribute UpDownMovement my distance is " + myDistance +
               " my time to turn is " + myPartDuration;
    }
    
    public Object clone()
    {
        return new UpDownMovement(myDistance, myPartDuration);
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        List<Integer> argList = new ArrayList<Integer>();
        argList.add(myDistance);
        argList.add(myPartDuration);
        
        return gson.toJson(argList);
    }
    
    public UpDownMovement fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Integer>>(){}.getType();
        List<Integer> argList = gson.fromJson(json, collectionType);
        return new UpDownMovement(argList.get(0), (argList.get(1)));
    }
    
    private UpDownMovement(){}
    
    public static AttributeFactory<UpDownMovement> getFactory()
    {
        return new AttributeFactory<UpDownMovement>(new UpDownMovement());
    }
   

}
