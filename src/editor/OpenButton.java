package editor;


import com.golden.gamedev.gui.TButton;


public class OpenButton extends TButton
{

    EditorView myGame;


    public OpenButton (String name,
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
        myGame.openFile();

    }

}
