package editor.json;

public interface Jsonable {
    public String toJson();
    public Object fromJson(String json);
}
