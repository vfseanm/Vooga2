package editor.buttons;


import com.golden.gamedev.gui.TButton;

import editor.EditorView;
import editor.LevelWriter;


public class SaveButton extends TButton
{

    EditorView myGame;
    LevelWriter myWriter;


    public SaveButton (String name,
                       int x,
                       int y,
                       int width,
                       int height,
                       EditorView s,
                       LevelWriter writer)
    {
        
        super(name, x, y, width, height);
        myGame = s;
        myWriter = writer;
    }

    public void doAction ()
    {
        myGame.saveFile(myWriter);
    }

}
