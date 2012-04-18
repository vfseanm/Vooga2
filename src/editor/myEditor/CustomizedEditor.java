package editor.myEditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.gui.TLabel;
import com.golden.gamedev.gui.toolkit.UIConstants;

import editor.EditorView;
import editor.buttons.ObjectPlacingButton;
import editor.buttons.DialogueMakingButton;
import editor.buttons.CustomButton;
import editor.dialogues.EditEnemyButtonDialogueBox;
import editor.dialogues.EditEnemyDialogue;
import editor.dialogues.EnemyDialogueBox;
import editor.dialogues.ExtendedDialogueBox;
import editor.dialogues.PlatformDialogueBox;
import editor.dialogues.PowerupDialogueBox;
import enemies.Enemy;

public class CustomizedEditor extends EditorView{

    public void initResources() {
        initialize();
        
        TLabel l2 = new TLabel("Enemies", 0, 10, 400, 40);
        l2.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        infoBox.add(l2);
        
        DialogueMakingButton newEnemyButton = new DialogueMakingButton("Create Enemy", 125, 50, 150, 40, this, new EnemyDialogueBox(myController));
        infoBox.add(newEnemyButton);
        
        TLabel l3 = new TLabel("Platforms", 0, 210, 400, 40);
        l3.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        infoBox.add(l3);
        
        DialogueMakingButton newPlatformButton = new DialogueMakingButton("Create Platform", 125, 250, 150, 40, this, new PlatformDialogueBox(myController));
        infoBox.add(newPlatformButton);
        
        TLabel l4 = new TLabel("Powerups", 0, 410, 400, 40);
        l4.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        infoBox.add(l4);
        
        CustomButton custom = new CustomButton("Customized dialogue", 125, 350, 150, 40, this);
        infoBox.add(custom);
        
        DialogueMakingButton newpowerUpButton = new DialogueMakingButton("Create Power-Up", 125, 450, 150, 40, this, new PowerupDialogueBox(myController));
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
                currentDialogueBox.setXY(this.getMouseX(), this.getMouseY());
            
            System.out.println("left click:" + getClickedSprite());
            System.out.println("right click:" + getRightClickedSprite());
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
