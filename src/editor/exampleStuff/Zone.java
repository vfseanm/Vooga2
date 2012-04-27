package editor.exampleStuff;

import java.awt.Point;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.editorConstructor;
import editor.input.inputTypes.Line;
import editor.input.inputTypes.Points;


public class Zone implements Cloneable {
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
    
    public Object clone()
    {
        Line line1 = (Line) myLines.get(0).clone();
        Line line2 = (Line) myLines.get(1).clone();
        ArrayList<Point> pointList = new ArrayList<Point>();
        for(Point p: myPoints)
        {
            pointList.add(p);
        }
        Points points = new Points();
        points.setPoints(pointList);
        GroupofEnemies group = (GroupofEnemies) myEnemies.clone();        
        
        return new Zone(line1, line2, points, defaultActive, myDamage, group );
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        List<String> paramList = new ArrayList<String>();
        paramList.add(gson.toJson(myLines.get(0)));
        paramList.add(gson.toJson(myLines.get(1)));
        paramList.add(gson.toJson(myPoints));
        paramList.add(gson.toJson(defaultActive));
        paramList.add(gson.toJson(myDamage));
        paramList.add(myEnemies.toJson());
        return gson.toJson(paramList);
    }
    
    public static Zone fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        List<String> paramList = gson.fromJson(json, collectionType);
        Line line1 = gson.fromJson(paramList.get(0), Line.class);
        Line line2 = gson.fromJson(paramList.get(1), Line.class);
        Type collectionType2 = new TypeToken<List<Point>>(){}.getType();
        ArrayList<Point> pointList = gson.fromJson(paramList.get(2), collectionType2);
        Points points = new Points();
        points.setPoints(pointList);
        boolean active = gson.fromJson(paramList.get(3), boolean.class);
        int damage = gson.fromJson(paramList.get(4), int.class);
        GroupofEnemies group = GroupofEnemies.fromJson(paramList.get(5));
        
        return new Zone(line1, line2, points, active, damage, group); 
    }
    
    
}
