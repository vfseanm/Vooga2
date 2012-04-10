package fighter;

import java.awt.image.BufferedImage;



import java.util.List;
import java.util.TreeMap;


import sprite.*;
import fighter.attributes.*;
import fighter.attributes.attributeremover.AttributeRemover;


@SuppressWarnings("serial")
public class Fighter extends AnimatedGameSprite {

	private Game							myGame;
	// map of key = attributes -> value = boolean representing inherency
	private TreeMap<String, Attribute>		myAttributes; 			
	private AttributeRemover				myAttributeRemover;	
	private Missile							myMissile;
	private FighterDeath					myDeathSequence;
	
	
	public Fighter(Game game, BufferedImage[] image, double x, double y, List<String> images) {
		super(image, x, y, images);
		myGame = game;
		myAttributes = new TreeMap<String, Attribute>();
	}

    public void update(long elapsedTime) {
		
		// if ability isn't inherent, performs appropriate function
		for (String ability: myAttributes.keySet()) {
			if (!myAttributes.get(ability).isInherent()) myAttributes.get(ability).doFunction();
		}
		
	}
	
	public void setAttributeRemover(AttributeRemover attributeRemover) {
		myAttributeRemover = attributeRemover;
	}
	
	public void changeAttributeRemover(AttributeRemover newAttributeRemover) {
		myAttributeRemover = newAttributeRemover;
	}
	
	public void setMissile(Missile missile) {
		myMissile = missile;
	}
	
	public Missile getMissile() {
		return myMissile;
	}
	
	public void setDeathSequence(FighterDeath deathSequence) {
		myDeathSequence = deathSequence;
	}
	
    public Attribute searchAttributes(String attribute) {
            if (myAttributes.containsKey(attribute)) {
                return myAttributes.get(attribute);
	    }
	    return null;
	}
	
	public void setAttributeFalse(Attribute attribute) {
	    attribute.setActive(false);
	}
	
	public void addExtraAttribute(Attribute extra) {
	    myAttributes.put(extra.getName(), extra);
	}
	
	public void removeExtraAttribute() {
		myAttributeRemover.removeAttribute(myAttributes);
	}


	public void updateLives(int numLives) {
		if (myAttributes.get("health").isActive())
			myAttributes.get("health").doFunction();
		else myAttributes.get("life").doFunction();
		}

	
	public void dies() {
		setActive(false);
		myDeathSequence.setLocation(getX(), getY());
		// 	ADD DEATH TO PLAYFIELD HERE
	}
}
