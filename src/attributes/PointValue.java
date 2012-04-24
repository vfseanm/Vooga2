package attributes;

import com.google.gson.Gson;

import editor.editorConstructor;
import editor.json.Jsonable;

@SuppressWarnings("serial")
public class PointValue extends Attribute implements Jsonable
{
    private int myPointValue;

    @editorConstructor(parameterNames = { "point value" }) 
    public PointValue (int value)
    {
        super(value);
        myPointValue = value;
    }


    @Override
    public String getName ()
    {
        return "PointValue";
    }


    public void modifyPointValue (int change)
    {
        myPointValue += change;
    }
    
    public String toString(){
        return "Attribute PointValue is" + myPointValue;
    }
    
    public Object clone()
    {
        return new PointValue(myPointValue);
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(myPointValue);
    }
    
    public static PointValue fromJson(String json)
    {
        Gson gson = new Gson();
        int points = gson.fromJson(json, int.class);
        return new PointValue(points);
    }
    
/*    private PointValue(){}
    
    public static ObjectFromJsonFactory getFactory()
    {
        return new ObjectFromJsonFactory(new PointValue());
    }
   */


}
