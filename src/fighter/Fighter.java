package fighter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import bonusobjects.PowerUp;

import com.golden.gamedev.engine.BaseInput;

import character.GameCharacter;
import fighter.movement.BasicMovement;
import attributes.Attribute;


@SuppressWarnings("serial")
public class Fighter extends GameCharacter {

	private List<Attribute>					myCarryableAttributes;
	private BaseInput						myUserInput;
	
	public Fighter(double x, double y, List<String> images, BaseInput userInput) {
		super(x, y, images);
		myCarryableAttributes = new ArrayList<Attribute>();
		myUserInput = userInput;
		setGroup("FIGHTER");
	}
	
    public void update(long elapsedTime) {
		performAttributeActions(elapsedTime);
		
		if (myUserInput.isKeyDown(KeyEvent.VK_C)) 
		{
		    // POP UP DIALOG ALLOWING YOU TO CHOOSE CARRYABLE OBJECT? CAN WE ACTUALLY PAUSE THE GAME?
			// OTHERWISE, SIMPLY HAVE KEYSTROKES = INDEX OF CARRYABLE ITEMS IN LIST? MAX = 6?
		}
	}
    
    
    // adds PowerUp - fix implementation to modify duplicate PowerUps?
    public void addPowerUp(PowerUp bonus) {
    	for (Attribute toAdd: bonus.getAttributesToOffer()) {
    		addAttribute(toAdd);
    	}
    }
    
    
    public void addCarryableAttribute(ArrayList<Attribute> carryables) {
    	myCarryableAttributes.addAll(carryables);
    	
    	// method for adding attributes to inventory of limited length = myMaxNumCarryables
/*    	for (int i = myCarryableAttributes.size(); i < myMaxNumCarryables; i++) {
    		myCarryableAttributes.add(i, carryables.get(i-myCarryableAttributes.size()));
    	}*/
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
	
	
	// method for getting speed to enable accurate side scrolling 
	public double getHorizMovement() {
			for (Attribute attribute : myAttributes) {
				if (attribute.getClass().getName().equalsIgnoreCase("fighter.movement.BasicMovement")) {
					BasicMovement myMovement = (BasicMovement) attribute;
					return myMovement.getHorizMovement();
				}
			}
		return 0;
	}
	
}
