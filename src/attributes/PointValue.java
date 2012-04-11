package attributes;

import editor.editorConstructor;

public class PointValue extends Attribute
{
    private int myPointValue;

    @editorConstructor(parameterNames = { "point value" }) 
    public PointValue (int value)
    {
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

}
