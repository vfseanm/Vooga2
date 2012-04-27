package attributes.fighterattributes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.golden.gamedev.engine.BaseInput;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;
import attributes.Attribute;
import attributes.interfaces.Input;
import attributes.interfaces.Movement;
import attributes.interfaces.Updateable;

@SuppressWarnings("serial")
public class FighterJump extends Attribute
    implements Updateable, Movement, Input, JsonableAttribute
{

    transient protected ResourceBundle myGameKeys =
        ResourceBundle.getBundle("demo.GameKeysResourceBundle");

    private BaseInput myUserInput;
    private double myJumpHeight;
    private double myDelay;
    private boolean canJump;
    private boolean isJumping;
    private int myTimer;
    private int jumpKey;


    @editorConstructor(parameterNames = { "jump height", "time" })
    public FighterJump (double jumpHeight, double delay)
    {
        super(jumpHeight, delay);
        if (jumpHeight < 0) throw new RuntimeException("You must enter a positive number for the jump height");
        myJumpHeight = jumpHeight;
        myDelay = delay;
        canJump = true;
        myTimer = 0;
        jumpKey = Integer.parseInt(myGameKeys.getString("JUMP"));
    }


    public void modifyJump (double jumpHeight, int time)
    {
        myJumpHeight += jumpHeight;
        myDelay += time;
    }


    public void modifyJump (boolean jump)
    {
        canJump = jump;
    }


    public void update (long elapsedTime)
    {
        if (isActive)
        {
            if (canJump && myUserInput.isKeyPressed(jumpKey))
            {
                isJumping = true;
                //canJump = false;
            }

            if (isJumping)
            {
                myTimer++;
                if (myTimer <= myDelay)
                {
                    myGameCharacter.moveY(-myJumpHeight);
                    myGameCharacter.allowAttribute("Gravity", false);
                }
                else
                {
                    myGameCharacter.allowAttribute("Gravity", true);
                    myTimer = 0;
                    isJumping = false;
                }
            }

        }
    }


    public void modifyFighterJump (boolean value)
    {
        canJump = value;
    }


    public void invert ()
    {
        myJumpHeight = -myJumpHeight;
    }


    @Override
    public Object clone ()
    {
        return new FighterJump(myJumpHeight, myDelay);
    }


    @Override
    public String getName ()
    {
        return "Jump";
    }


    public String toString ()
    {
        return "Attribute = Jump. My jump height = " + myJumpHeight +
               " ; my jump time = " + myDelay;
    }


    public double getHorizMovement ()
    {
        return 0;
    }


    public double getVertMovement ()
    {
        if (isActive && isJumping) return -myJumpHeight;
        return 0;
    }


    public String toJson ()
    {
        Gson gson = new Gson();
        List<Double> argList = new ArrayList<Double>();
        argList.add(myJumpHeight);
        argList.add(myDelay);
        return gson.toJson(argList);
    }


    public FighterJump fromJson (String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Double>>()
        {}.getType();
        List<Double> argList = gson.fromJson(json, collectionType);
        return new FighterJump(argList.get(0), argList.get(1));
    }


    public void setUserInput (BaseInput userInput)
    {
        myUserInput = userInput;
    }


    private FighterJump ()
    {};


    public static AttributeFactory<FighterJump> getFactory ()
    {
        return new AttributeFactory<FighterJump>(new FighterJump());
    }
}
