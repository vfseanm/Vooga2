package editor;


import com.golden.gamedev.gui.TButton;


public class SaveButton extends TButton
{

    SetGame myGame;


    public SaveButton (String name,
                       int x,
                       int y,
                       int width,
                       int height,
                       SetGame s)
    {
        super(name, x, y, width, height);
        myGame = s;
    }


    public void doAction ()
    {
        myGame.saveFile();
    }

}
