package editor.exampleStuff;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import editor.EditorController;
import editor.Framework;
import editor.InputListener;
import editor.dialogues.DialogueBox;
import editor.input.CustomInputManager;


@SuppressWarnings("serial")
public class WildandCrazyDialogueBox extends DialogueBox{
    
    private JTextField myName;
    private JTextField myGroup;
    private Zone myZone;
    @SuppressWarnings("unused")
	private GroupofEnemies myEnemies;

    public WildandCrazyDialogueBox(EditorController m) {
        super(m);

        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }

    
    public DialogueBox clone() {
        return new WildandCrazyDialogueBox(editorController);
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

        JButton enemyButton = new JButton("Choose my enemies ");
        enemyButton.addActionListener(new EnemyGroupListener());
        panel.add(enemyButton);
        
        JButton formatButton = new JButton("Set up my 'zone' ");
        formatButton.addActionListener(new FormatListener());
        panel.add(formatButton);
        
        String buttonPhrase = "Create this Wild and Crazy Thing";
        
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);
        
        return panel;
    }

    protected void BoxCompletedAction() {
        try{
            Framework framework = getFramework();
            editorController.addFrameworkAndButton(myName.getText(), framework);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "You must select an image" );
                return;
            }
            setVisible(false);
        
        
    }
    
    public class FormatListener implements ActionListener, InputListener {
        JCheckBox box;
        CustomInputManager input;
        
        @SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e)
        {
            Class c = Zone.class;
            dialogueController.promptForInput(c, this);
        }
        
        public void setObject(Object zone) {
            myZone = (Zone) zone;
            
        }
    }
    public class EnemyGroupListener implements ActionListener, InputListener {
        JCheckBox box;
        CustomInputManager input;
        
        @SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e)
        {
            Class c = GroupofEnemies.class;
            dialogueController.promptForInput(c, this);
        }
        
        public void setObject(Object enemies) {
            myEnemies = (GroupofEnemies) enemies;
            
        }
    }
    
    
    public Framework getFramework() throws RuntimeException{
        if(myImagePaths==null)
        {
            throw new RuntimeException();
        }
        
        WildAndCrazyObject prototype = new WildAndCrazyObject(0,0, myImagePaths);
        prototype.setZone(myZone);
        
        prototype.setGroup(myGroup.getText());
        Framework framework = new Framework(myName.getText(), "WildandCrazy", prototype);
        return framework;
    }

}
