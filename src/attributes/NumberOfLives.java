package attributes;

import editor.editorConstructor;
import editor.file.Jsonable;
import editor.file.ObjectFromJsonFactory;
@SuppressWarnings("serial")
public class NumberOfLives extends Attribute implements Jsonable
{
    private int myLives;

    @editorConstructor(parameterNames = { "number of lives" })
    public NumberOfLives (int lives)
    {
        super(lives);
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
        if (myLives <= 0) {

        	
        		myGameCharacter.setActive(false);


        }
    }
    
    public String toString(){
        return "Attribute NumberOfLives is currently" + myLives;
    }
    
    public Object clone()
    {
        return new NumberOfLives(myLives);
    }
    
    public String toJson()
    {
        return myLives+"";
    }
    
    public static NumberOfLives fromJson(String json)
    {
        int lives = Integer.parseInt(json);
        return new NumberOfLives(lives);
    }
    
/*    private NumberOfLives(){}
    
    public static ObjectFromJsonFactory getFactory()
    {
        return new ObjectFromJsonFactory(new NumberOfLives());
    }*/
   
   

}
