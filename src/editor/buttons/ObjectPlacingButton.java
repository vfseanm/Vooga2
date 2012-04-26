package editor.buttons;


import com.golden.gamedev.gui.TButton;
import editor.EditorController;
import editor.Framework;

public class ObjectPlacingButton extends TButton
{
    private boolean pressed;
    private EditorController myController;
    private Framework myFramework;


    public ObjectPlacingButton (String name,
                    int x,
                    int y,
                    int width,
                    int height,
                    Framework f, EditorController controller)
    {
        super(name, x, y, width, height);
        myFramework = f;
        myController = controller;
    }

    public boolean getClicked ()
    {
        boolean t = pressed;
        pressed = false;
        return t;
    }

    public void doAction ()
    {
        pressed = true;
        myController.setFramework(myFramework);
    }
    
    public Framework getFramework()
    {
        return myFramework;
    }

}
