package attributes;

import java.io.Serializable;

import enemies.Enemy;
/**
 * This class is used to allow for dynamic attribute allocation to enemies
 * In essence this allows for flexibility in the framework whilst still providing
 * functionality by defining some subclasses of Attribute
 * The enemy class has a List of attributes
 * @author Alex
 *
 */
public abstract class  Attribute implements Serializable
{
    protected Enemy myEnemy;
    public abstract String getName();
    
    //associates an attribute to its enemy
    public void setEnemy(Enemy enemy){
        myEnemy = enemy;
    }
}
