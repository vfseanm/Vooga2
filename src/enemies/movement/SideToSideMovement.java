package enemies.movement;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.editorConstructor;
import editor.json.Jsonable;

//Change location to coordinate

@SuppressWarnings("serial")
public class SideToSideMovement extends TwoPartMovement implements Jsonable
{
    

    @editorConstructor(parameterNames = { "distance" , "duration"})
    public SideToSideMovement (int distance, int duration)
    {
        super(distance,duration);
        
    }
    

    protected void movementPart1 ()
    {
        myGameCharacter.setX(myGameCharacter.getX()-myDistance);
        

    }


    protected void movementPart2 ()
    {
       myGameCharacter.setY(myGameCharacter.getX()+myDistance);
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
    public String toJson()
    {
        Gson gson = new Gson();
        List<Integer> argList = new ArrayList<Integer>();
        argList.add(myDistance);
        argList.add(myPartDuration);
        
        return gson.toJson(argList);
    }
    
    public static SideToSideMovement fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Integer>>(){}.getType();
        List<Integer> argList = gson.fromJson(json, collectionType);
        return new SideToSideMovement(argList.get(0), (argList.get(1)));
    }
    
/*    private SideToSideMovement(){}
    public static ObjectFromJsonFactory getFactory()
    {
        return new ObjectFromJsonFactory(new SideToSideMovement());
    }*/
   

}
