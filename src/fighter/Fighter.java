package fighter;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.engine.BaseInput;

import character.GameCharacter;
import attributes.Attribute;


@SuppressWarnings("serial")
public class Fighter extends GameCharacter {

	private List<Attribute>					myCarryableAttributes;
	private BaseInput						myUserInput;
	
	public Fighter(BufferedImage[] image, double x, double y, List<String> images, BaseInput userInput) {
		super(image, x, y, images);
		myCarryableAttributes = new ArrayList<Attribute>();
		myUserInput = userInput;
		setGroup("FIGHTER");
	}
	
    public void update(long elapsedTime) {
		performAttributeActions(elapsedTime);
		
		if (myUserInput.isKeyDown(KeyEvent.VK_C)) 
		{
		    // POP UP DIALOG ALLOWING YOU TO CHOOSE CARRYABLE OBJECT? CAN WE ACTUALLY PAUSE THE GAME?
			// OTHERWISE,  
		}
	}
    
    public void addCarryableAttribute(Attribute carryable) {
    	myCarryableAttributes.add(carryable);
    }
    
    public void useCarryableAttribute(int indexCarryableAttribute)  {
    	try {
    		myAttributes.add(myCarryableAttributes.get(indexCarryableAttribute));
    		myCarryableAttributes.remove(indexCarryableAttribute);
    	}
    	catch (IndexOutOfBoundsException e) {
    		System.out.println("This Carryable Attribute is not in your inventory.");
    	}
    }
	
	public String getName() {
		return "Fighter";
	}
}
