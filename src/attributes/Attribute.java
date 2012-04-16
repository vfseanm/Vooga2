package attributes;

import java.io.Serializable;

import character.GameCharacter;

import enemies.Enemy;
import fighter.*;

/**
 * This class is used to allow for dynamic attribute allocation to enemies and fighters.
 * In essence, this allows for flexibility in the framework while still providing
 * functionality by defining some subclasses of Attribute.
 * The enemy class has a List of attributes, and the fighter class has a Map of attributes.
 * The abstract methods defined in the GameCharacter interface allows us to handle attributes
 * uniformly.
 * @authors Alex & Tori
 *
 */
@SuppressWarnings("serial")
public abstract class  Attribute implements Serializable
{
    protected GameCharacter		myGameCharacter;	
    protected boolean 			isActive;
    
    public abstract String getName();
    
    //associates an attribute with the given enemy/fighter
    public void setGameCharacter(GameCharacter gameCharacter) {
        myGameCharacter = gameCharacter;
    }
    
    // turns an attribute on/off (active/inactive)
    public void setActivity(boolean active) {
    	isActive = active;
    }
}
