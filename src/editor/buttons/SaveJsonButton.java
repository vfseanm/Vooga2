package editor.buttons;


import com.golden.gamedev.gui.TButton;

import editor.EditorView;


public class SaveJsonButton extends TButton
{

    EditorView myGame;


    public SaveJsonButton (String name,
                       int x,
                       int y,
                       int width,
                       int height,
                       EditorView s)
    {
        
        super(name, x, y, width, height);
        myGame = s;
    }

    public void doAction ()
    {
        myGame.saveJsonFile();
    }

}