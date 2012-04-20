package enemies.state;

import enemies.Enemy;
import fighter.Fighter;


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


    public void excuteBehavior (Enemy enemy, long elapsedTime)
    {
        Fighter fighter = enemy.getFighter();
        if(!changeState(enemy, fighter)){
            if(enemy.getY()>fighter.getY()){
                enemy.setY(enemy.getY()-1);
            }else enemy.setY(enemy.getY()+1);
            
        }

    }


    public boolean changeState (Enemy enemy,Fighter fighter)
    {
         
        if (!enemy.isOnScreen())
        {
            enemy.setState(PassiveState.getInstance());
            return true;
        }
        if(enemy.getY()==fighter.getY()){
            enemy.setState(AggressiveState.getInstance());
            return true;
        }
        return false;

    }


    //Will update as the class changes
    public String toString ()
    {
        return "DefensiveState";
    }

}
