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
import attributes.Updateable;


@SuppressWarnings("serial")
public class OneDirectionMovement extends Attribute implements Updateable, JsonableAttribute
{
    private String myDirection;
    private int myDistance;


    @editorConstructor(parameterNames = { "direction", "distance" })
    public OneDirectionMovement (String direction, int distance)
    {
        super(direction, distance);
        myDirection = direction;
        myDistance = distance;
        isActive = false;
    }


    public void modifyOneDirectionMovement (String direction, int distance)
    {
        myDirection = direction;
        myDistance += distance;
    }


    public void update (long elaspedTime)
    {
        if (myGameCharacter.isOnScreen() || isActive)
        {
            if (myDirection.equalsIgnoreCase("left"))
            {
                myGameCharacter.setX(myGameCharacter.getX() - myDistance);
            }
            else if (myDirection.equalsIgnoreCase("right"))
            {
                myGameCharacter.setX(myGameCharacter.getX() + myDistance);
            }
            else if (myDirection.equalsIgnoreCase("up"))
            {
                myGameCharacter.setY( myGameCharacter.getY() - myDistance);
            }
            else if (myDirection.equalsIgnoreCase("down"))
            {
                myGameCharacter.setY(myGameCharacter.getY() + myDistance);
            }

        }
    }


    public void invert ()
    {
        myDistance = -myDistance;
    }


    @Override
    public String getName ()
    {
        return "OneDirectionMovement";
    }


    public String toString ()
    {
        return "Attribute OneDirectionMovement My Direction is " + myDirection +
               " my distance is " + myDistance;
    }
    
    public Object clone()
    {
        return new OneDirectionMovement(myDirection, myDistance);
    }
    
    
    public String toJson()
    {
        Gson gson = new Gson();
        List<String> argList = new ArrayList<String>();
        argList.add(myDirection);
        argList.add(myDistance+"");
        
        return gson.toJson(argList);
    }
    
    public  OneDirectionMovement fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        List<String> argList = gson.fromJson(json, collectionType);
        return new OneDirectionMovement(argList.get(0), Integer.parseInt((argList.get(1))));
    }
    
    private OneDirectionMovement(){}
    
    public static AttributeFactory<OneDirectionMovement> getFactory()
    {
        return new AttributeFactory<OneDirectionMovement>(new OneDirectionMovement());
    }
   
}
