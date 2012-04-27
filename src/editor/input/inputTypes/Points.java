package editor.input.inputTypes;

import java.awt.Point;


import java.io.Serializable;
import java.util.ArrayList;

import sprite.AnimatedGameSprite;
import editor.Framework;



@SuppressWarnings("serial")
public class Points implements InputType, Serializable{
private ArrayList<Point> myPoints;

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
public void setLeftClickedFramework(Framework f) {
    return;
}
public void setRightClickedFramework(Framework f) {
    return; 
}

}
