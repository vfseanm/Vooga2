package editor.input;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.engine.BaseInput;

import editor.dialogues.DialogueBox;

public class Line implements InputType, Serializable{
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

}
