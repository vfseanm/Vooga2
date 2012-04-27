package editor.json;

import attributes.Attribute;



public class AttributeFactory<T extends JsonableAttribute > {
    T myAttribute;
    public AttributeFactory(T a)
    {
        myAttribute = a;
    }
    
    public boolean isThisKindOfAttribute(String s)
    {
        return myAttribute.getClass().toString().equals(s);
    }
    
    public Attribute parseFromJson(String json)
    {
        return  myAttribute.fromJson(json);
    }
}

