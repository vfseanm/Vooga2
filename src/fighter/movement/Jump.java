package fighter.movement;

import java.awt.event.KeyEvent;

import com.golden.gamedev.engine.BaseInput;

import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;



@SuppressWarnings("serial")
public class Jump extends Attribute implements Updateable
{
	private BaseInput 		myUserInput;
    private int 			myJumpHeight;
    private int 			myTime;
    private boolean 		isJumping;
    private int 			time;


    @editorConstructor(parameterNames = { "user input", "distance", "time" })
    public Jump (BaseInput userInput, int jumpHeight, int delay)
    {
        super(userInput, jumpHeight, delay);
        myUserInput = userInput;
        myJumpHeight = jumpHeight;
        myTime = delay;
        isJumping = false;
        time = 0;     
    }

    public void modifyJumpingMovement (int distance, int time)
    {
        myJumpHeight += distance;
        myTime += time;
    }


    public void update (long elapsedTime)
    {
        if (isActive)
        {
        	if (myUserInput.isKeyDown(KeyEvent.VK_UP)) 
    		{
    		    isJumping = true;
    		}
        	
            if (time <= myTime)
            {
                myGameCharacter.moveY(-myJumpHeight);
                myGameCharacter.allowAttribute("Gravity", false);
            }
            else
            {            
                myGameCharacter.allowAttribute("Gravity",true);
                isJumping = false;
            }
        }
        
        time++;
    }


    @Override
    public String getName ()
    {
        return "Jump";
    }


    public String toString ()
    {
        return "Attribute = Jump. My jump height = " + myJumpHeight +
               " ; my jump time = " + myTime;
    }

	public void invert() {
		// TODO Auto-generated method stub
		
	}

}
