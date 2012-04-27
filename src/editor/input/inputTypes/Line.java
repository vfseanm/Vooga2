package editor.input.inputTypes;

import java.awt.Point;


import java.io.Serializable;
import java.util.ArrayList;
import sprite.AnimatedGameSprite;
import editor.Framework;



@SuppressWarnings("serial")
public class Line implements InputType, Serializable{
private ArrayList<Point> myLine;

public Line()
{
    myLine = new ArrayList<Point>();
}

    public String getPrompt() {
        return "Draw a line";
    }

    public ArrayList<Point> getLine() {
        return myLine;
    }

    public void setXY(int x, int y) {
        if (myLine == null)
            myLine = new ArrayList<Point>();
        Point p = new Point(x, y);
        myLine.add(p);
    }

    public void setLine(ArrayList<Point> l) {
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




public Object clone()
{
    Line line = new Line();
    ArrayList<Point> pointList = new ArrayList<Point>();
    for(Point p: myLine)
    {
        pointList.add((Point) p.clone());
    }
    line.setLine(pointList);
    return line;
    
}


}
