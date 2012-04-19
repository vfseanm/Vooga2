package editor.buttons;

import com.golden.gamedev.gui.TButton;

import editor.EditorView;
import editor.dialogues.DialogueBox;


public class ButtonMakingButton extends TButton
{
    private boolean pressed;
    //private BufferedImage myImage;
    private String imageName;
    private String myType;
    private EditorView myView;
    private DialogueBox myDialogueBox;

    public ButtonMakingButton(String name,
                    int x,
                    int y,
                    int width,
                    int height, EditorView view, DialogueBox box)
    {
        super(name, x, y, width, height);
        myView = view;
        myDialogueBox = box;
    }


    public boolean getClicked ()
    {
        boolean t = pressed;
        pressed = false;
        return t;
    }


/*    public BufferedImage getImage ()
    {
        return myImage;
    }*/


    public void doAction ()
    {
        myView.openDialogue(myDialogueBox);
           
    }

    public String getType ()
    {
        return myType;
    }


    public String getImageName ()
    {
        return imageName;
    }

}
