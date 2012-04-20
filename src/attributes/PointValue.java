package attributes;

import editor.editorConstructor;

@SuppressWarnings("serial")
public class PointValue extends Attribute
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
        return myPointValue+"";
    }
    
    public static PointValue fromJson(String json)
    {
        int points = Integer.parseInt(json);
        return new PointValue(points);
    }

}
