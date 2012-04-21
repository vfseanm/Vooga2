package editor.buttons;


import com.golden.gamedev.gui.TButton;

import editor.EditorView;
import editor.dialogues.DialogueBox;


public class DialogueMakingButton extends TButton
{
    private EditorView myView;
    private DialogueBox myDialogueBox;

    public DialogueMakingButton(String name,
                    int x,
                    int y,
                    int width,
                    int height, EditorView view, DialogueBox dialogueBox)
    {
        super(name, x, y, width, height);
        myView = view;
        myDialogueBox = dialogueBox;
    }

    public void doAction ()
    {
        myView.openDialogue(myDialogueBox.clone());
    }
}
