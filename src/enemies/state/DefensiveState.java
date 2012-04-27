package enemies.state;

import enemies.Enemy;
import fighter.Fighter;


/**
 * The defensive state implements a singleton pattern passive state implements
 * enemy state
 * 
 * @author Alex
 */

public class DefensiveState implements EnemyState
{
    //Bill Pugh Thread Safe Lazy Initialization Singleton Solution
    private DefensiveState ()
    {

    }

    private static class DefensiveStateHolder
    {
        public final static DefensiveState instance = new DefensiveState();
    }


    public static DefensiveState getInstance ()
    {
        return DefensiveStateHolder.instance;
    }


    /**
     * checks to see if it needs to change state otherwise it moves the enemy to
     * the y coordinate of the fighter
     */
    public void excuteBehavior (Enemy enemy, long elapsedTime)
    {
        Fighter fighter = Fighter.getInstance();
        if (!changeState(enemy, fighter))
        {
            if (enemy.getY() > fighter.getY())
            {
                enemy.setY(enemy.getY() - 1);
            }
            else enemy.setY(enemy.getY() + 1);

        }

    }

    /**
     * if the y coordinate is the same for enemy and fighter the aggressive state is triggered
     * if the enemy is off screen a passive behavior is triggered
     */

    public boolean changeState (Enemy enemy, Fighter fighter)
    {

        if (!enemy.isOnScreen())
        {
            enemy.setState(PassiveState.getInstance());
            return true;
        }
        if (enemy.getY() == fighter.getY())
        {
            enemy.setState(AggressiveState.getInstance());
            return true;
        }
        return false;

    }



    public String toString ()
    {
        return "DefensiveState";
    }

}
