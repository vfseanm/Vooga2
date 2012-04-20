package enemies.state;

import enemies.Enemy;


public class DefensiveState implements EnemyState
{

    public void excuteBehavior (Enemy enemy)
    {
        //TODO write Defensive AI

    }


    public void changeState (Enemy enemy)
    {
        if(!enemy.isOnScreen()){
            enemy.setState(PassiveState.getInstance());
        }

    }
  //Will update as the class changes
    public String toString ()
    {
        return "DefensiveState";
    }

}
