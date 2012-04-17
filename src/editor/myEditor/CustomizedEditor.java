package editor.myEditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.golden.gamedev.gui.TLabel;
import com.golden.gamedev.gui.toolkit.UIConstants;

import editor.EditorView;
import editor.buttons.ButtonMakingButton;
import editor.buttons.CustomButton;
import editor.dialogues.ExtendedDialogueBox;

public class CustomizedEditor extends EditorView{

    public void initResources() {
        initialize();
        
        TLabel l2 = new TLabel("Enemies", 0, 10, 400, 40);
        l2.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        infoBox.add(l2);
        
        ButtonMakingButton newEnemyButton = new ButtonMakingButton("Create Enemy", 125, 50, 150, 40, this, "enemy");
        infoBox.add(newEnemyButton);
        
        TLabel l3 = new TLabel("Platforms", 0, 210, 400, 40);
        l3.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        infoBox.add(l3);
        
        ButtonMakingButton newPlatformButton = new ButtonMakingButton("Create Platform", 125, 250, 150, 40, this, "platform");
        infoBox.add(newPlatformButton);
        
        TLabel l4 = new TLabel("Powerups", 0, 410, 400, 40);
        l4.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        infoBox.add(l4);
        
        CustomButton custom = new CustomButton("open dialogue", 125, 350, 150, 40, this);
        infoBox.add(custom);
        
        ButtonMakingButton newpowerUpButton = new ButtonMakingButton("Create Power-Up", 125, 450, 150, 40, this, "power up");
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
            //if (bsInput.isMousePressed(MouseEvent.BUTTON1))
                //currentDialogueBox.setXY(this.getMouseX(), this.getMouseY());
            if (bsInput.isMouseDown(MouseEvent.BUTTON1))
                currentDialogueBox.setXY(this.getMouseX(), this.getMouseY());
            
        }
        
    }

}
