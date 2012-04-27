package weapons;

import character.AttributeUser;


/**
 * interface to define weapons which attacks can use weapons define how they
 * would be used and define all the necessary stuff they would want to define a
 * unique weapon and attacking style
 * 
 * @author Alex
 */
public interface Weapon
{
    /**
     * the method which is called by attack from the attack attribute
     * @param character the character to use the weapon
     */
    public void use (AttributeUser character);

    /**
     * inverts the weapon
     */
    public void invert ();

}
