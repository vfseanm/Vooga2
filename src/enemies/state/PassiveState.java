package enemies.state;

import enemies.Enemy;


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


    public void excuteBehavior (Enemy enemy)
    {
        ///TODO write passive AI

    }


    public void changeState (Enemy enemy)
    {
        // TODO write when to change and how to change

    }


    //Will update as the class changes
    public String toString ()
    {
        return "PassiveState";
    }

}
