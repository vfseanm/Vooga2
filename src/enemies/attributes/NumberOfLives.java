package enemies.attributes;

public class NumberOfLives extends Attribute
{
    private int myLives;


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
