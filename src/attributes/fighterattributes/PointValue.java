package attributes.fighterattributes;

import attributes.Attribute;

import com.google.gson.Gson;

import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;

@SuppressWarnings("serial")
public class PointValue extends Attribute implements JsonableAttribute
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
    
    public PointValue fromJson(String json)
    {
        Gson gson = new Gson();
        int points = gson.fromJson(json, int.class);
        return new PointValue(points);
    }
    
    private PointValue(){}
    
    public static AttributeFactory<PointValue> getFactory()
    {
        return new AttributeFactory<PointValue>(new PointValue());
    }
   


}
