package editor.json;

import attributes.Attribute;



public class AttributeFactory<T extends Attribute & Jsonable > {
    T myAttribute;
    public AttributeFactory(T a)
    {
        myAttribute = a;
    }
    
    public boolean isThisKindOfSprite(String s)
    {
        return myAttribute.getClass().toString().equals(s);
    }
    
    public Attribute parseFromJson(String json)
    {
        return (Attribute) myAttribute.fromJson(json);
    }
}

