package attributes;

import java.io.Serializable;

import enemies.Enemy;
import fighter.*;

/**
 * This class is used to allow for dynamic attribute allocation to enemies
 * In essence this allows for flexibility in the framework whilst still providing
 * functionality by defining some subclasses of Attribute
 * The enemy class has a List of attributes
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public abstract class  Attribute implements Serializable
{
    protected Enemy 		myEnemy;
    protected Fighter		myFighter;
    
    public abstract String getName();
    
    //associates an attribute with the given enemy
    public void setEnemy(Enemy enemy) {
        myEnemy = enemy;
    }
    
    // associates an attribute with the fighter
    public void setFighter(Fighter fighter) {
    	myFighter = fighter;
    }
}
