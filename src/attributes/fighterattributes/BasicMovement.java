package attributes.fighterattributes;

import character.GameCharacter;

import com.golden.gamedev.engine.BaseInput;

import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;

import java.awt.event.KeyEvent;
import attributes.*;

/**
 * This Attribute allows basic movement (right/left) by the parameterized distance
 * myHorizMovement. The GameCharacter that has the attribute will move based on user 
 * input, which must be set after the Attribute is constructed.
 *  
 * @author Tori
 */

@SuppressWarnings("serial")
public class BasicMovement extends Attribute implements Updateable, Movement, Input, JsonableAttribute {

	public BaseInput 	myUserInput;
	public double 		myHorizMovement;
	public boolean		facingRight;
	
	@editorConstructor(parameterNames = { "horizontal movement" })
	public BasicMovement(double horizMove) {
	    super(horizMove);
		 if (horizMove < 0) 
	        	throw new RuntimeException("You must enter a positive number for the horizontal movement");
		myHorizMovement = horizMove;
	}
	
	
	@Override
	public String getName() {
		return "BasicMovement";
	}


	public void update(long elapsedTime) {
		if (isActive) {
			if (myUserInput.isKeyDown(KeyEvent.VK_LEFT)) {
				myGameCharacter.moveX(-myHorizMovement);
				facingRight = false;
			}

			if (myUserInput.isKeyDown(KeyEvent.VK_RIGHT)) {
				myGameCharacter.moveX(myHorizMovement);
				facingRight = true;
			}
		}
	}

	
	public void invert() {
		myHorizMovement = -myHorizMovement;
	}	
	
	
	public Object clone()
	{
	    return new BasicMovement(myHorizMovement);
	}

	
	public double getHorizMovement() {
		if (isActive) return myHorizMovement;
		return 0;
	}
	
	public double getVertMovement() {
		return 0;
	}
	
	/**
	 * Method for getting the direction the GameCharacter is facing to enable                           
	 * shooting in proper direction
	 *         
	 * @return boolean = whether the GameCharacter is facing right (true/false)
	 */
	public boolean getWhetherFacingRight() {
		return facingRight;
	}
	
	public void setGameCharacter(GameCharacter gameCharacter) {
		myGameCharacter = gameCharacter;
	}


	public void setUserInput(BaseInput userInput) {
	    myUserInput = userInput;
	}
	
    public String toJson()
    {
        return myHorizMovement + "";
    }
    
   public  BasicMovement fromJson(String json)
    {

        double movement = Double.parseDouble(json);
        return new BasicMovement(movement);
    }
   
   
   private BasicMovement(){};
   
   public static AttributeFactory<BasicMovement> getFactory()
   {
       return new AttributeFactory<BasicMovement>(new BasicMovement());
   }
}
