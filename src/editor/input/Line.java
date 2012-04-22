package editor.input;

import java.awt.Point;
import java.util.ArrayList;

import com.golden.gamedev.engine.BaseInput;

import editor.dialogues.DialogueBox;

public class Line {
private ArrayList<Point> myLine;
private DialogueBox myBox;
private BaseInput myInput;

public Line(DialogueBox box, BaseInput bsInput)
{
    myBox = box;
    myInput = bsInput;
}
public static String getPrompt()
{
    return "Draw a line";
}
}
