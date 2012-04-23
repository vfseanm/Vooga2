package editor.exampleStuff;

import java.util.ArrayList;

import editor.editorConstructor;
import editor.input.Line;

import sprite.AnimatedGameSprite;


public class Zone {
    private ArrayList<Line> myLines;
    private GroupofEnemies myEnemies;
    
    @editorConstructor(parameterNames = { "", "", "", "", "" })
    public Zone(Line l, Line l2, Line l3, Line l4, GroupofEnemies e)
    {
        myLines = new ArrayList<Line>();
        myLines.add(l);
        myLines.add(l2);
        myLines.add(l3);
        myLines.add(l4);
        myEnemies = e;
    }
}
