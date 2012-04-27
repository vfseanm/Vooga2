package editor.myEditor;
import java.awt.Graphics2D;
import editor.EditorView;
import editor.buttons.DialogueOpeningButton;
import editor.dialogues.EnemyDialogueBox;
import editor.dialogues.PlatformDialogueBox;
import editor.dialogues.PowerupDialogueBox;
import editor.exampleStuff.EditEnemyButtonDialogueBox;
import editor.exampleStuff.EditEnemyDialogue;
import editor.exampleStuff.WildandCrazyDialogueBox;
import enemies.Enemy;

public class CustomizedEditor extends EditorView {

    public void initResources() {
        initialize();

        DialogueOpeningButton newEnemyButton = new DialogueOpeningButton(
                "Create Enemy", 125, 10, 150, 40, this, new EnemyDialogueBox(
                        myController));
        infoBox.add(newEnemyButton);

        DialogueOpeningButton newPlatformButton = new DialogueOpeningButton(
                "Create Platform", 125, 170, 150, 40, this,
                new PlatformDialogueBox(myController));
        infoBox.add(newPlatformButton);


        DialogueOpeningButton newpowerUpButton = new DialogueOpeningButton(
                "Create Power-Up", 125, 330, 150, 40, this,
                new PowerupDialogueBox(myController));
        infoBox.add(newpowerUpButton);

        
        DialogueOpeningButton custom = new DialogueOpeningButton(
                "Customized dialogue", 125, 490, 150, 40, this,
                new WildandCrazyDialogueBox(myController));
        infoBox.add(custom);

        setFrameworkBottom();
    }

    public void render(Graphics2D pen) {
        renderEditor(pen);

    }

    public void update(long time) {
        updateEditor(time);

        if (getRightClickedSprite() != null) {
            if (getRightClickedSprite() instanceof Enemy) {
                Enemy e = (Enemy) getRightClickedSprite();
                EditEnemyDialogue editEnemyBox = new EditEnemyDialogue(
                        myController, e);
                this.openDialogue(editEnemyBox);
            }
        }
        if (getRightClickedFramework() != null) {
            if(getRightClickedFramework().getPrototype() instanceof Enemy)
            {
            EditEnemyButtonDialogueBox myView = new EditEnemyButtonDialogueBox(
                    myController, getRightClickedFramework());
            this.openDialogue(myView);
            }
        }
    }
}
