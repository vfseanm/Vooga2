package editor.exampleStuff;

import java.awt.Point;
import java.util.ArrayList;
import editor.editorConstructor;
import editor.input.inputTypes.Line;
import editor.input.inputTypes.Points;


public class Zone {
    private ArrayList<Line> myLines;
    private GroupofEnemies myEnemies;
    private boolean defaultActive;
    private ArrayList<Point> myPoints;
    private int myDamage;

    @editorConstructor(parameterNames = { "", "", "", "default visibility", "damage", "" })
    public Zone(Line l, Line l2, Points points, boolean tf, int damage, GroupofEnemies e) {
        myLines = new ArrayList<Line>();
        myLines.add(l);
        myLines.add(l2);
        myPoints = points.getPoints();
        defaultActive = tf;
        myDamage = damage;
        myEnemies = e;
    }
}
