package enemies.state;

import enemies.Enemy;
import fighter.Fighter;


/**
 * The aggressive state implements a singleton pattern passive state implements
 * enemy state
 * 
 * @author Alex
 */
public class AggressiveState implements EnemyState
{

    /**
     * singleton lazy and thread safe initialization
     */
    private AggressiveState ()
    {

    }

    private static class AggressiveStateHolder
    {
        public final static AggressiveState instance = new AggressiveState();
    }


    public static AggressiveState getInstance ()
    {
        return AggressiveStateHolder.instance;
    }


    /**
     * checks if the state needs to be changed otherwise it tries to attack and
     * moves toward the fighter
     */
    public void excuteBehavior (Enemy enemy, long elapsedTime)
    {

        Fighter fighter = Fighter.getInstance();

        if (!changeState(enemy, fighter))
        {

            enemy.attack(elapsedTime);
            if (enemy.getX() > fighter.getX())
            {

                enemy.setX(enemy.getX() - 1);
            }
            else
            {

                enemy.setX(enemy.getX() + 1);
            }

        }

    }


    /**
     * if the enemy is not on screen passive behavior is triggered if the enemy
     * is not within a range of the y coordinate the defensive behavior is
     * triggered returns a boolean for if the state changed
     */

    public boolean changeState (Enemy enemy, Fighter fighter)
    {

        if (!enemy.isOnScreen())
        {

            enemy.setState(PassiveState.getInstance());
            return true;
        }
        if (Math.abs(fighter.getY() - enemy.getY()) > 20)
        {

            enemy.setState(DefensiveState.getInstance());
            return true;
        }
        return false;

    }


    public String toString ()
    {
        return "AggressiveState";
    }

}
