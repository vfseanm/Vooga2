package attributes;

import com.google.gson.Gson;

import editor.editorConstructor;
import editor.json.Jsonable;
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
        Gson gson = new Gson();
        return gson.toJson(myLives);
    }
    
    public static NumberOfLives fromJson(String json)
    {
        Gson gson = new Gson();
        int lives = gson.fromJson(json, int.class);
        return new NumberOfLives(lives);
    }
    
/*    private NumberOfLives(){}
    
    public static ObjectFromJsonFactory getFactory()
    {
        return new ObjectFromJsonFactory(new NumberOfLives());
    }*/
   
   

}
