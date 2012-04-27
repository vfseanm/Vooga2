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

/**
 * OneDirectional movement extends attribute and implements updateable and jsonableattribute
 * @author Alex
 *
 */

@SuppressWarnings("serial")
public class OneDirectionMovement extends Attribute implements Updateable, JsonableAttribute
{
    private String myDirection;
    private double myDistance;


    @editorConstructor(parameterNames = { "distance", "direction" })
    public OneDirectionMovement ( double distance, String direction)
    {
        super(distance, direction);
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
        if (myAttributeUser.isOnScreen() || isActive)
        {
            if (myDirection.equalsIgnoreCase("left"))
            {
                myAttributeUser.setX(myAttributeUser.getX() - myDistance);
            }
            else if (myDirection.equalsIgnoreCase("right"))
            {
                myAttributeUser.setX(myAttributeUser.getX() + myDistance);
            }
            else if (myDirection.equalsIgnoreCase("up"))
            {
                myAttributeUser.setY( myAttributeUser.getY() - myDistance);
            }
            else if (myDirection.equalsIgnoreCase("down"))
            {
                myAttributeUser.setY(myAttributeUser.getY() + myDistance);
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
        return new OneDirectionMovement(myDistance, myDirection);
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
        return new OneDirectionMovement(Double.parseDouble((argList.get(1))), argList.get(0) );
    }
    
    private OneDirectionMovement(){}
    
    public static AttributeFactory<OneDirectionMovement> getFactory()
    {
        return new AttributeFactory<OneDirectionMovement>(new OneDirectionMovement());
    }
   
}
