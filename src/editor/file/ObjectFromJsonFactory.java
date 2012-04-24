package editor.file;

public  class ObjectFromJsonFactory{
    private Jsonable object;
    
    public ObjectFromJsonFactory(Jsonable o)
    {
        object = o;
    }
    
    public Object getObject(String json)
    {
        return object.fromJson(json);
    }
    
    
}
