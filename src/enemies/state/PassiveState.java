package enemies.state;

import enemies.Enemy;
import fighter.Fighter;


/**
 * The passive state implements a singleton pattern passive state implements
 * enemy state
 * 
 * @author Alex
 */
public class PassiveState implements EnemyState
{
    private int proximity;


    //Bill Pugh Thread Safe Lazy Initialization Singleton Solution
    private PassiveState ()
    {
        //defaults to 300 pixels away before triggering another state
        proximity = 300;
    }

    private static class PassiveStateHolder
    {
        public final static PassiveState instance = new PassiveState();
    }


    public static PassiveState getInstance ()
    {
        return PassiveStateHolder.instance;
    }


    /**
     * Checks to see if it needs to change state other wise it updates the enemy
     * like normal because it has passive behavior
     */
    public void excuteBehavior (Enemy enemy, long elapsedTime)
    {
        Fighter opponent = Fighter.getInstance();
        if (!changeState(enemy, opponent))
        ;
        enemy.performUpdateableAttributes(elapsedTime);

    }


    /**
     * changes state if the enemy is on screen and within the proximity range
     */
    public boolean changeState (Enemy enemy, Fighter opponent)
    {

        if (enemy.getDistance(opponent) < proximity && enemy.isOnScreen())
        {
            enemy.setState(DefensiveState.getInstance());
            return true;
        }
        return false;

    }


    /**
     * allows you to change the distance to trigger other behaviors
     * 
     * @param range the distance before a state change is triggered
     */
    public void setProximityForPassive (int range)
    {
        proximity = range;
    }


    public String toString ()
    {
        return "PassiveState";
    }

}
