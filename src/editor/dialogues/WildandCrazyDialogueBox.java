package editor.dialogues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import attributes.Attribute;

import editor.EditorController;
import editor.InputListener;
import editor.WildAndCrazyObject;
import editor.dialogues.DialogueBox.ImageAction;
import editor.frameworks.Framework;
import editor.input.CustomInputManager;
import editor.input.Zone;
import enemies.Enemy;

public class WildandCrazyDialogueBox extends DialogueBox{
    
    private JTextField myName;
    private JTextField myGroup;
    private Zone myZone;

    public WildandCrazyDialogueBox(EditorController m) {
        super(m);

        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }

    
    public DialogueBox clone() {
        return new WildandCrazyDialogueBox(myController);
    }

    
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
        
        JLabel label1 = new JLabel("Enter your Wild and Crazy Name:");
        panel.add(label1);
        myName = new JTextField(10);
        panel.add(myName, BorderLayout.SOUTH);
        
        JLabel groupLabel = new JLabel("Group:");
        panel.add(groupLabel);

        myGroup = new JTextField(10);

        panel.add(myGroup, BorderLayout.SOUTH);
        
        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Create this Wild and Crazy Thing";
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);
        
        JButton formatButton = new JButton("Set up my 'zone' ");
        formatButton.addActionListener(new FormatListener());
        panel.add(formatButton);
        
        return panel;
    }

    protected void BoxCompletedAction() {
        Framework framework = getFramework();
        myController.addButton(myName.getText(), framework);
        setVisible(false);
        
    }
    
    public class FormatListener implements ActionListener, InputListener {
        Class associatedClass;
        JCheckBox box;
        CustomInputManager input;
        
        public void actionPerformed(ActionEvent e)
        {
            Class c = Zone.class;
            controller.promptForInput(c, this);
            
        }
        
        public void setObject(Object zone) {
            myZone = (Zone) zone;
            
        }
    }
    
    
    public Framework getFramework()
    {
        
        WildAndCrazyObject prototype = new WildAndCrazyObject(0,0, myImagePaths);
        prototype.setZone(myZone);
        
        prototype.setGroup(myGroup.getText());
        Framework framework = new Framework(myName.getText(), "WildandCrazy", prototype);
        return framework;
    }

}
