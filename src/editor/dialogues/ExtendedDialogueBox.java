package editor.dialogues;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import editor.EditorController;

@SuppressWarnings("serial")
public class ExtendedDialogueBox extends DynamicBox{

    private JTextField myName;

    public ExtendedDialogueBox(EditorController m) {
        super(m);

        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }

    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
       
        
        JLabel groupLabel = new JLabel("Name:");
        panel.add(groupLabel);

        myName = new JTextField(10);

        panel.add(myName, BorderLayout.SOUTH);

        JButton imageButton = new JButton("Print out line");
        imageButton.addActionListener(new DrawPathAction());
        panel.add(imageButton);

        String buttonPhrase = "Create Enemy";
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);
        
        return panel;
    }
    
class GoAction implements ActionListener {       
        
        public void actionPerformed(ActionEvent e)
        {
            //Framework framework = getFramework();
            //System.out.println("framework "+framework);
            //myController.addButton(myName.getText(), framework);
            myController.closeDialogue();
            setVisible(false);
        }
    }


}
