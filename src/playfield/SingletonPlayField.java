package playfield;

import com.golden.gamedev.object.PlayField;
/**
 * Singleton Pattern to use for playfields instead of passing them around everywhere
 * @author Alex
 *
 */
public class SingletonPlayField extends PlayField
{
    private SingletonPlayField(){
        
    }
    private static class SingletonPlayFieldHolder{
        public static final SingletonPlayField instance= new SingletonPlayField();
    }
    
    public static SingletonPlayField getInstance(){
        return SingletonPlayFieldHolder.instance;
    }

}
