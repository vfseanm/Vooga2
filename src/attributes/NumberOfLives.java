package attributes;

import editor.editorConstructor;
public class NumberOfLives extends Attribute
{
    private int myLives;

    @editorConstructor(parameterNames = { "number of lives" })
    public NumberOfLives (int lives)
    {
        myLives = lives;
    }


    @Override
    public String getName ()
    {
        return "NumberOfLives";
    }


    public void modifyNumberOfLives (int change)
    {
        myLives += change;
        if (myLives <= 0)
        {
            myEnemy.setActive(false);
        }
    }
    
    public String toString(){
        return "Attribute NumberOfLives is currently" + myLives;
    }

}
