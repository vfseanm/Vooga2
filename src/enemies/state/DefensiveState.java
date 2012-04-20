package enemies.state;

import enemies.Enemy;


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


    public void excuteBehavior (Enemy enemy)
    {
        //TODO write Defensive AI

    }


    public void changeState (Enemy enemy)
    {
        if (!enemy.isOnScreen())
        {
            enemy.setState(PassiveState.getInstance());
        }

    }


    //Will update as the class changes
    public String toString ()
    {
        return "DefensiveState";
    }

}
