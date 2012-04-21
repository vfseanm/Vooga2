package editor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import sprite.AnimatedGameSprite;

import enemies.Enemy;

@SuppressWarnings("serial")
public class WildAndCrazyObject extends AnimatedGameSprite{
    private ArrayList<ArrayList<Point>> myPaths;
    private HashSet<Enemy> myEnemies;
    private ArrayList<AnimatedGameSprite> myOtherThings;
    private Object myNonGameObject;
    
    public WildAndCrazyObject(ArrayList<ArrayList<Point>> severalLines, HashSet<Enemy> enemies, ArrayList<AnimatedGameSprite> customObjects, Object somethingElse, double x, double y, List<String> images)
    {
        super(x, y, images);
        myPaths = severalLines;
        myEnemies = enemies;
        myNonGameObject = somethingElse;
        myOtherThings = customObjects;
    }
    
    public Object clone ()
    {
        
        ArrayList<ArrayList<Point>> lines = new ArrayList<ArrayList<Point>>();
        lines.addAll(myPaths);
        
        HashSet<Enemy> enemies = new HashSet<Enemy>();
        enemies.addAll(myEnemies);
        
        ArrayList<AnimatedGameSprite> customStuff = new ArrayList<AnimatedGameSprite>();
        customStuff.addAll(myOtherThings);
        
        Object otherObject = new Object();
        otherObject = myNonGameObject;
        
        List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        
        WildAndCrazyObject clone = new WildAndCrazyObject(lines, enemies, customStuff, otherObject, this.getX(), this.getY(), imageNames);
        return clone;
        
        
    }
    
}
