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
 * Jumping movement for the enemy class to use Jumping movement extends
 * attribute implements updateable and jsonableattribute
 * 
 * @author Alex
 */
@SuppressWarnings("serial")
public class JumpingMovement extends Attribute
    implements Updateable, JsonableAttribute
{
    private double myDistance;
    private int myTime;
    private int time;


    /**
     * the distance for each update call to be changed time is the time that the
     * jump lasts distance should be about 0 to 3 for decent speeds distances
     * exceeding 10 will not smooth or useful
     * 
     * @param distance the distance the character will move during an update
     *            call
     * @param delay the ammount of time the jump lasts
     */
    @editorConstructor(parameterNames = { "distance", "time" })
    public JumpingMovement (double distance, int delay)
    {

        super(distance, delay);
        myDistance = distance;
        myTime = delay;
        time = 0;

    }


    /**
     * allows the jumping movement to be modified
     * 
     * @param distance the amount of distance to change the instance variable
     *            myDistance
     * @param time the amount of time to change the instance variable myTime
     */
    public void modifyJumpingMovement (double distance, int time)
    {
        myDistance += distance;
        myTime += time;
    }


    public void setActivity (boolean active)
    {
        myAttributeUser.allowAttribute("Gravity", true);
        isActive = active;
    }


    /**
     * if jumping is active and the timer is allowed the enemy will jump and
     * turn off gravity after jumping can no longer happen the gravity is
     * restored
     */
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


    /**
     * inverts the myDistance attribute
     */
    public void invert ()
    {
        myDistance = -myDistance;
    }


    /**
     * returns the name of the attribute
     */
    @Override
    public String getName ()
    {
        return "JumpingMovement";
    }


    /**
     * returns the toString representation of JumpingMovement
     */
    public String toString ()
    {
        return "Attribute JumpingMovement my jump distance is " + myDistance +
               " my jump time is " + myTime + " activity is " + isActive;

    }


    /**
     * clones the jumping movement and returns the clone
     */
    public Object clone ()
    {
        return new JumpingMovement(myDistance, myTime);
    }


    /**
     * converts to json
     */
    public String toJson ()
    {
        Gson gson = new Gson();
        List<String> argList = new ArrayList<String>();
        argList.add(myDistance + "");
        argList.add(myTime + "");
        return gson.toJson(argList);
    }


    /**
     * converts from json to construct the jumping movement
     */
    public JumpingMovement fromJson (String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>()
        {}.getType();
        List<String> argList = gson.fromJson(json, collectionType);
        return new JumpingMovement(Double.parseDouble(argList.get(0)),
                                   Integer.parseInt(argList.get(1)));
    }

    /**
     * used by the factory to check if an attribute is this attribute
     */
    private JumpingMovement ()
    {}

    /**
     * factory used for determining an attribute when using json
     * @return AttributeFactory
     */
    public static AttributeFactory<JumpingMovement> getFactory ()
    {
        return new AttributeFactory<JumpingMovement>(new JumpingMovement());
    }

}
