package editor.buttons;


import com.golden.gamedev.gui.TButton;

import editor.EditorView;
import editor.dialogues.DialogueBox;

/**
 * Button that opens a dialogue box
 * @author Sean
 *
 */
public class DialogueOpeningButton extends TButton
{
    private EditorView myView;
    private DialogueBox myDialogueBox;
    /**
     * 
     * @param name: the name of the button
     * @param x: the x location of the button
     * @param y: they y location of the button
     * @param width: width of box
     * @param height: height of box
     * @param view: reference to the level editor view
     * @param dialogueBox: dialogue box that is opened when the button is clicked
     */
    public DialogueOpeningButton(String name,
                    int x,
                    int y,
                    int width,
                    int height, EditorView view, DialogueBox dialogueBox)
    {
        super(name, x, y, width, height);
        myView = view;
        myDialogueBox = dialogueBox;
    }
    
    /**
     * Action performed when button is clicked
     */
    public void doAction ()
    {
        myView.openDialogue(myDialogueBox.clone());
    }
}
