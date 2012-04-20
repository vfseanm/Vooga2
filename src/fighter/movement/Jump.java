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
    private double 			myJumpHeight;
    private int 			myTime;
    private boolean 		isJumping;
    private int 			time;


    @editorConstructor(parameterNames = { "user input", "jump height", "time" })
    public Jump (BaseInput userInput, double jumpHeight, int delay)
    {
        super(userInput, jumpHeight, delay);
        myUserInput = userInput;
        myJumpHeight = Math.abs(jumpHeight);
        myTime = delay;
        isJumping = false;
        time = 0;     
    }

    
    public void modifyJump (double jumpHeight, int time)
    {
        myJumpHeight += jumpHeight;
        myTime += time;
    }
    
    
    public void setActivity(boolean active){
    	if (!active) {
    		myGameCharacter.allowAttribute("Gravity", true);
    	}
    	else {
    		myGameCharacter.allowAttribute("Gravity", false);
    	}
    	isActive=active;
    }

    
    public void update (long elapsedTime)
    {
        if (isActive)
        {
        	if (myUserInput.isKeyPressed(KeyEvent.VK_UP)) 
    		{
    		    isJumping = true;
    		    myGameCharacter.allowAttribute("Gravity", false);
    		}
        	
            if (isJumping && time <= myTime)
            {
                myGameCharacter.moveY(-myJumpHeight);
            }
            else
            {    
            	isJumping = false;
                myGameCharacter.allowAttribute("Gravity",true);               
            }
        }
        
        time++;
    }


	public void invert() {
		myJumpHeight = -myJumpHeight;
	}

	@Override
	public Object clone() {
		return new Jump(myUserInput, myJumpHeight, myTime);
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

}
