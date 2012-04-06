package enemies.state;

import enemies.Enemy;


/**
 * Using the state pattern to implement AI Allows for users to utilize
 * functional AI or build their own
 * 
 * @author Alex
 */
public interface EnemyState
{

    public void excuteBehavior (Enemy enemy);


    public void changeState (Enemy enemy);

}
