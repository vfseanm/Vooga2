package enemies.state;

import weapons.Fireball;
import enemies.Enemy;
import fighter.Fighter;


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


    public void excuteBehavior (Enemy enemy, long elapsedTime)
    {
        
        Fighter fighter = enemy.getFighter();
        if(!changeState(enemy,fighter)){
           enemy.attack(elapsedTime);
           if(enemy.getX()>fighter.getX()){
               enemy.setX(enemy.getX()-1);
           }
           else
              enemy.setX(enemy.getX()+1); 
           
        }

    }


    public boolean changeState (Enemy enemy,Fighter fighter)
    {
        
        if (!enemy.isOnScreen())
        {
            enemy.setState(PassiveState.getInstance());
            return true;
        }
        if(fighter.getY()!=enemy.getY()){
            enemy.setState(DefensiveState.getInstance());
            return true;
        }
        return false;

    }


    //Will update as the class changes
    public String toString ()
    {
        return "AggressiveState";
    }

}
