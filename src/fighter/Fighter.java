package fighter;

import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import bonusobjects.PowerUp;

import com.golden.gamedev.engine.BaseInput;

import character.GameCharacter;
import fighter.movement.Movement;
import attributes.Attribute;


@SuppressWarnings("serial")
public class Fighter extends GameCharacter {

	private List<Attribute>					myCarryableAttributes;
	private BaseInput						myUserInput;
	
	
	public Fighter(double x, double y, List<String> images) {
		super(x, y, images);
		myCarryableAttributes = new ArrayList<Attribute>();
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
    
    
    /**
	 * Adds Attributes, removing older, duplicate versions                       
	 */
    public void addAttribute(Attribute toAdd) {	
    	Attribute currentVersion = getAttributeByName(toAdd.getName());
    	if (currentVersion != null) {
    		myAttributes.remove(currentVersion);
    	}
    	super.addAttribute(toAdd);
	}
    
    
    
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
	
	
	
	/**
	 * Method for getting horizontal and vertical movement distances                           
	 * to ensure accurate side scrolling.
	 *         
	 * @return array with [0] = horizontal movement & [1] = vertical movement
	 */
	public double[] getMovement() {
		double[] horizVertMovement = new double[2];
		for (Attribute attribute : myAttributes) {
			if (attribute.getClass().getInterfaces().length != 0 &&
	                attribute.getClass().getInterfaces()[1].equals(Movement.class)) {
				Movement scrollSpeed = (Movement) attribute;
				horizVertMovement[0] += scrollSpeed.getHorizMovement();
				horizVertMovement[1] += scrollSpeed.getVertMovement();
			}
		}
		return horizVertMovement;
	}
	
	
	public void setUserInput(BaseInput userInput) {
		myUserInput = userInput;
	}
	
	public BaseInput getUserInput() {
		return myUserInput;
	}
	
	public String getName() {
		return "Fighter";
	}
	
}
