package enemies.state;

import enemies.Enemy;


public class AggressiveState implements EnemyState
{
    //Bill Pugh Thread Safe Lazy Initialization Singleton Solution
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


    public void excuteBehavior (Enemy enemy)
    {
        //TODO write aggressive AI

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
        return "AggressiveState";
    }

}
