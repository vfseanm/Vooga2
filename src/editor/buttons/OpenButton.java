package editor.buttons;


import com.golden.gamedev.gui.TButton;

import editor.EditorView;
import editor.LevelLoader;


public class OpenButton extends TButton
{

    EditorView myGame;
    LevelLoader myLoader;


    public OpenButton (String name,
                       int x,
                       int y,
                       int width,
                       int height,
                       EditorView s,
                       LevelLoader loader)
    {
        super(name, x, y, width, height);
        myGame = s;
        myLoader = loader;
    }


    public void doAction ()
    {
        myGame.openFile(myLoader);

    }

}
