package editor.input;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.engine.BaseInput;

import editor.dialogues.DialogueBox;

public class Points implements InputType, Serializable{
private ArrayList<Point> myPoints;
private ArrayList<AnimatedGameSprite> mySprites;

public String getPrompt()
{
    return "Click on as many Points as you want";
}

public ArrayList<Point> getPoints()
{
    return myPoints;
}


public void setXY(int x, int y) {
    if(myPoints==null)
        myPoints = new ArrayList<Point>();
    Point p = new Point(x, y);
    myPoints.add(p);
}
public void setPoints(ArrayList<Point> l)
{
    myPoints = l;
}


public void setRightClickedSprite(AnimatedGameSprite sprite) {
    return;
}


public void setLeftClickedSprite(AnimatedGameSprite sprite) {
    return;
}

}
