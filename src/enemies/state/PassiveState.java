package enemies.state;

import enemies.Enemy;
import fighter.Fighter;


public class PassiveState implements EnemyState
{
    //Bill Pugh Thread Safe Lazy Initialization Singleton Solution
    private PassiveState ()
    {

    }

    private static class PassiveStateHolder
    {
        public final static PassiveState instance = new PassiveState();
    }


    public static PassiveState getInstance ()
    {
        return PassiveStateHolder.instance;
    }


    public void excuteBehavior (Enemy enemy, long elapsedTime)
    {
        Fighter opponent = enemy.getFighter();
        if (!changeState(enemy, opponent))
        ;
        enemy.updateUpdateableAttributes(elapsedTime);

    }


    public boolean changeState (Enemy enemy, Fighter opponent)
    {

        if (enemy.getDistance(opponent) < 300)
        {
            enemy.setState(DefensiveState.getInstance());
            return true;
        }
        return false;

    }


    //Will update as the class changes
    public String toString ()
    {
        return "PassiveState";
    }

}
