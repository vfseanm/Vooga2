package fighter;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import character.GameCharacter;

import sprite.*;
import carryables.Carryable;

import attributes.Attribute;
import attributes.Updateable;


@SuppressWarnings("serial")
public class Fighter extends GameCharacter {

	private List<Attribute>					myCarryableAttributes;
	
	
	public Fighter(BufferedImage[] image, double x, double y, List<String> images) {
		super(image, x, y, images);
		myCarryableAttributes = new ArrayList<Attribute>();
		setGroup("FIGHTER");
	}

	
    public void update(long elapsedTime) {
		for (Attribute attribute : myAttributes) {

			if (attribute.getClass().getInterfaces().length != 0
					&& attribute.getClass().getInterfaces()[0]
							.equals(Updateable.class)) {
				try {

					((Updateable) attribute).update(elapsedTime);
				} catch (ClassCastException e) {

					e.printStackTrace();
				}
			}
		}
	}
	
	public String getName() {
		return "Fighter";
	}
}
