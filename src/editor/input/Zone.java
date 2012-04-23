package editor.input;

import java.util.ArrayList;

import editor.editorConstructor;

import sprite.AnimatedGameSprite;


public class Zone {
    private ArrayList<Line> myLines;
    private GroupofEnemies myEnemies;
    
    @editorConstructor(parameterNames = { "", "", "", "", "" })
    public Zone(Line l, Line l2, Line l3, Line l4, GroupofEnemies e)  //ArrayList<AnimatedGameSprite> customObjects, Object somethingElse,
    {
        myLines = new ArrayList<Line>();
        myLines.add(l);
        myLines.add(l2);
        myLines.add(l3);
        myLines.add(l4);
        myEnemies = e;
    }
}
