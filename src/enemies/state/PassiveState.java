package enemies.state;

import enemies.Enemy;
import fighter.Fighter;


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


    public void excuteBehavior (Enemy enemy, long elapsedTime)
    {
        Fighter opponent = Fighter.getInstance();
        if (!changeState(enemy, opponent))
        ;
        enemy.updateUpdateableAttributes(elapsedTime);

    }


    public boolean changeState (Enemy enemy, Fighter opponent)
    {

        if (enemy.getDistance(opponent) < proximity&&enemy.isOnScreen())
        {
            enemy.setState(DefensiveState.getInstance());
            return true;
        }
        return false;

    }
    public void setProximityForPassive(int range){
        proximity = range;
    }


    //Will update as the class changes
    public String toString ()
    {
        return "PassiveState";
    }

}
