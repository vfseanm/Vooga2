package fighter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import character.GameCharacter;
import attributes.Attribute;


@SuppressWarnings("serial")
public class Fighter extends GameCharacter {

	private List<Attribute>					myCarryableAttributes;
	
	
	public Fighter(BufferedImage[] image, double x, double y, List<String> images) {
		super(image, x, y, images);
		myCarryableAttributes = new ArrayList<Attribute>();
		setGroup("FIGHTER");
	}
	
    public void update(long elapsedTime) {
		performAttributeActions(elapsedTime);
		
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


	public void accessAttributeMethod(String methodStart, String name,
			Object... o) {
		// TODO Auto-generated method stub
		
	}

	public void restoreOriginalAttribute(String name, Object... o) {
		// TODO Auto-generated method stub
		
	}
}
