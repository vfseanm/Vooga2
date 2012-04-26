package editor.json;

import attributes.Attribute;



public interface JsonableAttribute {
    
    public String toJson();
    public Attribute fromJson(String json);

}
