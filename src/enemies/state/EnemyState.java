package enemies.state;

import enemies.Enemy;
import fighter.Fighter;


/**
 * Using the state pattern to implement AI Allows for users to utilize
 * functional AI or build their own
 * 
 * @author Alex
 */
public interface EnemyState
{

    public void excuteBehavior (Enemy enemy,long elapsedTime);


    public boolean changeState (Enemy enemy,Fighter fighter);

}
