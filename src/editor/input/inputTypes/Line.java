package editor.input.inputTypes;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.engine.BaseInput;
import com.google.gson.Gson;

import editor.dialogues.DialogueBox;
import editor.file.Jsonable;
import editor.frameworks.Framework;

public class Line implements InputType, Serializable, Jsonable{
private ArrayList<Point> myLine;

public String getPrompt()
{
    return "Draw a line";
}

public ArrayList<Point> getLine()
{
    return myLine;
}


public void setXY(int x, int y) {
    if(myLine==null)
        myLine = new ArrayList<Point>();
    Point p = new Point(x, y);
    myLine.add(p);
}
public void setLine(ArrayList<Point> l)
{
    myLine = l;
}


public void setRightClickedSprite(AnimatedGameSprite sprite) {
    return;
}


public void setLeftClickedSprite(AnimatedGameSprite sprite) {
    return;
}


public void setLeftClickedFramework(Framework f) {
    return;
}


public void setRightClickedFramework(Framework f) {
    return; 
}

public String toJson()
{
    Gson gson = new Gson();
    return gson.toJson(this);
    
}

public static Line fromJson(String json)
{
    Gson gson = new Gson();
    return gson.fromJson(json, Line.class);
}

}
