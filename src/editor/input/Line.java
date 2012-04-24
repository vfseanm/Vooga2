package editor.input;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.engine.BaseInput;

import editor.dialogues.DialogueBox;

public class Line implements InputType, Serializable{
private ArrayList<Point> myLine;
private ArrayList<AnimatedGameSprite> mySprites;

public String getPrompt()
{
    return "Draw a line";
}

public ArrayList<Point> getLine()
{
    System.out.println("returning line!");
    return myLine;
}


public void setXY(int x, int y) {
    if(mySprites==null)
        mySprites = new ArrayList<AnimatedGameSprite>();
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
