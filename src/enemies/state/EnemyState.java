package enemies.state;

import enemies.Enemy;
import fighter.Fighter;


/**
 * Using the state pattern to implement AI Allows for users to utilize
 * functional AI or build their own
 * 
 * @author Alex
 */
public interface EnemyState
{
    /**
     * Takes in a given enemy and the time between a GTGE update call Should
     * call change state if only if the state does not change excute behavior
     * 
     * @param enemy the enemy which the state pattern will affect
     * @param elapsedTime time between update calls
     */
    public void excuteBehavior (Enemy enemy, long elapsedTime);


    /**
     * change state is used to change the state of an enemy it returns true if
     * the state changes it also changes the state to the appropriately
     * determined state allowing flexibility in how you want to change between
     * different behaviors
     * 
     * @param enemy the enemy whoses states are to be changed
     * @param fighter the fighter who can influence states to change for example
     *            upon proximity
     * @return boolean whether the state changed
     */
    public boolean changeState (Enemy enemy, Fighter fighter);

}
