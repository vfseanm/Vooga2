package attributes.fighterattributes;

import attributes.Attribute;

import com.google.gson.Gson;

import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;

@SuppressWarnings("serial")
public class FighterScore extends Attribute implements JsonableAttribute
{
    private int myScore;

    @editorConstructor(parameterNames = { "point value" }) 
    public FighterScore (int score)
    {
        super(score);
        myScore = score;
    }


    @Override
    public String getName ()
    {
        return "FighterScore";
    }


    public void modifyPointValue (int change)
    {
        myScore += change;
    }
    
    public String toString(){
        return "Attribute is FighterScore = " + myScore;
    }
    
    public Object clone()
    {
        return new FighterScore(myScore);
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(myScore);
    }
    
    public FighterScore fromJson(String json)
    {
        Gson gson = new Gson();
        int points = gson.fromJson(json, int.class);
        return new FighterScore(points);
    }
    
    private FighterScore(){}
    
    public static AttributeFactory<FighterScore> getFactory()
    {
        return new AttributeFactory<FighterScore>(new FighterScore());
    }

}
