package attributes.fighterattributes;

import character.AttributeUser;



import com.golden.gamedev.engine.BaseInput;

import demo.SingletonKeyController;

import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;

import attributes.*;
import attributes.interfaces.Input;
import attributes.interfaces.Movement;
import attributes.interfaces.Updateable;

/**
 * This Attribute allows basic movement (right/left) by the parameterized distance
 * myHorizMovement. The AttributeUser that has the attribute will move based on user 
 * input, which must be set after the Attribute is constructed.
 *  
 * @author Tori
 */

@SuppressWarnings("serial")
public class FighterBasicMovement extends Attribute implements Updateable, Movement, Input, JsonableAttribute {
	
	public BaseInput 	myUserInput;
	public double 		myHorizMovement;
	public boolean		movingRight;
	public boolean		movingLeft;
	
	@editorConstructor(parameterNames = { "horizontal movement" })
	public FighterBasicMovement(double horizMove) {
	    super(horizMove);

		 if (horizMove < 0) 
		 {
			 System.out.println("You must enter a positive number for the horizontal movement");
		 }
		myHorizMovement = Math.abs(horizMove);
	}
	
	
	@Override
	public String getName() {
		return "BasicMovement";
	}


	public void update(long elapsedTime) {
		if (isActive) {
			if (myUserInput.isKeyDown(SingletonKeyController.getInstance().getKeyCode(("LEFT")))) {
				myAttributeUser.moveX(-myHorizMovement);
				myAttributeUser.modifyAttribute("FighterMissile", false);
				movingRight = false;
				movingLeft = true;
			}

			if (myUserInput.isKeyDown(SingletonKeyController.getInstance().getKeyCode(("RIGHT")))) {
				myAttributeUser.moveX(myHorizMovement);
				myAttributeUser.modifyAttribute("FighterMissile", true);
				movingRight = true;
				movingLeft = false;
			}
		}
	}

	
	public void invert() {
		myHorizMovement = -myHorizMovement;
	}	
	
	
	public Object clone()
	{
	    return new FighterBasicMovement(myHorizMovement);
	}

	
	public double getHorizMovement() {
		if (movingRight) return myHorizMovement;
		if (movingLeft)	 return -myHorizMovement;
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
	public boolean isFacingRight() {
		return movingRight;
	}
	
	public void setAttributeUser(AttributeUser gameCharacter) {
		myAttributeUser = gameCharacter;
	}


	public void setUserInput(BaseInput userInput) {
	    myUserInput = userInput;
	}
	
    public String toJson()
    {
        return myHorizMovement + "";
    }
    
   public  FighterBasicMovement fromJson(String json)
   {
        double movement = Double.parseDouble(json);
        return new FighterBasicMovement(movement);
   }
   
   
   private FighterBasicMovement(){};
   
   public static AttributeFactory<FighterBasicMovement> getFactory()
   {
       return new AttributeFactory<FighterBasicMovement>(new FighterBasicMovement());
   }
}
