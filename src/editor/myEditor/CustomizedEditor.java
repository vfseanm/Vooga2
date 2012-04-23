package editor.myEditor;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;


import com.golden.gamedev.gui.TLabel;
import com.golden.gamedev.gui.toolkit.UIConstants;

import editor.EditorView;
import editor.buttons.DialogueOpeningButton;
import editor.dialogues.EditEnemyDialogue;
import editor.dialogues.EnemyDialogueBox;
import editor.dialogues.ExtendedDialogueBox;
import editor.dialogues.PlatformDialogueBox;
import editor.dialogues.PowerupDialogueBox;
import editor.dialogues.WildandCrazyDialogueBox;
import enemies.Enemy;

public class CustomizedEditor extends EditorView{

    public void initResources() {
        initialize();
        
        TLabel l2 = new TLabel("Enemies", 0, 10, 400, 40);
        l2.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        infoBox.add(l2);
        
        DialogueOpeningButton newEnemyButton = new DialogueOpeningButton("Create Enemy", 125, 50, 150, 40, this, new EnemyDialogueBox(myController));
        infoBox.add(newEnemyButton);
        
        TLabel l3 = new TLabel("Platforms", 0, 210, 400, 40);
        l3.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        infoBox.add(l3);
        
        DialogueOpeningButton newPlatformButton = new DialogueOpeningButton("Create Platform", 125, 250, 150, 40, this, new PlatformDialogueBox(myController));
        infoBox.add(newPlatformButton);
        
        TLabel l4 = new TLabel("Powerups", 0, 410, 400, 40);
        l4.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        infoBox.add(l4);
        
        DialogueOpeningButton custom = new DialogueOpeningButton("Customized dialogue", 125, 350, 150, 40, this, new WildandCrazyDialogueBox(myController));
        infoBox.add(custom);
        
        DialogueOpeningButton newpowerUpButton = new DialogueOpeningButton("Create Power-Up", 125, 450, 150, 40, this, new PowerupDialogueBox(myController));
        infoBox.add(newpowerUpButton);
        
        setFrameworkBottom();
    }


    public void render(Graphics2D pen) {
        renderEditor(pen);
        
    }

    public void update(long time) {
        updateEditor(time);
        
        if(currentDialogueBox!=null)
        {
            if (bsInput.isMouseDown(MouseEvent.BUTTON1))
            {
                currentDialogueBox.setClick(this.getMouseX(), this.getMouseY());
            }
            if(getRightClickedSprite()!=null)
            {
                currentDialogueBox.setRightClickSprite(getRightClickedSprite());
            }
            if(getRightClickedSprite()!=null)
            {
                currentDialogueBox.setLeftClickSprite(getRightClickedSprite());
            }
        }
        if(getRightClickedSprite()!=null)
        {
            if(getRightClickedSprite() instanceof Enemy)
            {
                Enemy e = (Enemy) getRightClickedSprite();
                EditEnemyDialogue editEnemyBox = new EditEnemyDialogue(myController, e, this.getMouseX(), this.getMouseY());
                this.openDialogue(editEnemyBox);
            }
        }
        
        
        
    }
    
    

}
