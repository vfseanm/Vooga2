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


// Idea master config file to control attribute exlusivity
// flying


@SuppressWarnings("serial")
public class UpDownMovement extends TwoPartMovement implements Updateable,JsonableAttribute
{

    @editorConstructor(parameterNames = { "distance" , "duration"})
    public UpDownMovement (double distance, int duration)
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
        myAttributeUser.setY( myAttributeUser.getY() + myDistance);
        

    }
 

    protected void movementPart2 ()
    {
        myAttributeUser.setY( myAttributeUser.getY() - myDistance);
        
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
 
    //TODO fix json problem
    public String toJson()
    {
        Gson gson = new Gson();
        List<String> argList = new ArrayList<String>();
        argList.add(myDistance+"");
        argList.add(myPartDuration+"");
        
        return gson.toJson(argList);
    }
    
    public UpDownMovement fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        List<String> argList = gson.fromJson(json, collectionType);
        return new UpDownMovement(Double.parseDouble(argList.get(0)),Integer.parseInt((argList.get(1))));
    }
    
    private UpDownMovement(){}
    
    public static AttributeFactory<UpDownMovement> getFactory()
    {
        return new AttributeFactory<UpDownMovement>(new UpDownMovement());
    }
   

}
