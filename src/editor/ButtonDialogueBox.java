package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComponent;

public abstract class ButtonDialogueBox extends DialogueBox {

    public ButtonDialogueBox(EditorController m) {
        super(m);
    }
    
    public abstract JComponent makeSelectionPanel() throws ClassNotFoundException, IOException;
    public abstract Framework getFramework();

class GoAction implements ActionListener {       
        
        public void actionPerformed(ActionEvent e)
        {
            Framework framework = getFramework();
            System.out.println("framework "+framework);
            myController.addButton(myName.getText(), framework);
            setVisible(false);
        }
    }
}
