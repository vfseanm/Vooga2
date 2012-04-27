package editor.dialogues;


import java.io.IOException;


import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import editor.EditorController;
import editor.Framework;
import enemies.Enemy;


import attributes.Attribute;

/**
 * Dialogue box used to create new Enemy framework
 * @author Becky and Sean
 *
 */
@SuppressWarnings("serial")
public class EnemyDialogueBox extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    
    private AttributeSelectionPanel attributePanel;
    private JTextField myName;
    private JTextField myGroup;

    /**
     * 
     * @param m level editor controller from which this dialogue box is opened
     */
    public EnemyDialogueBox(EditorController m)
    {
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }

    /**
     * makes the selection panel for the dialogue box, allowing the user to specify the information
     * needed to created a new Enemy Framework
     */
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        List<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("attributes.enemyattributes");
        packagesToSearch.add("attributes.sharedattributes");
        attributePanel = new AttributeSelectionPanel(packagesToSearch, dialogueController);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
        panel.add(attributePanel);
        
        JLabel label1 = new JLabel("Enemy Name:");
        panel.add(label1);
        
        myName = new JTextField(10);
        
        panel.add(myName, BorderLayout.SOUTH);
        
        JLabel groupLabel = new JLabel("Collision Group:");
        panel.add(groupLabel);

        myGroup = new JTextField(10);

        panel.add(myGroup, BorderLayout.SOUTH);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Create Enemy";
        		
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);
        

        return panel;
    }
    /**
     * creates the framework from the information gathered from the selection panel
     * @return the framework created
     * @throws RuntimeException
     */
    public Framework getFramework() throws RuntimeException
    {
        if(myImagePaths==null)
        {
            throw new RuntimeException();
        }
        Enemy prototype = new Enemy(0,0, myImagePaths);
       
        ArrayList<Attribute> attributes = attributePanel.getSelectedAttributes();
        for(Attribute a: attributes)
        {
            prototype.addAttribute(a);
        }
        if(myGroup.getText().length()!=0)
        {
            prototype.setGroup(myGroup.getText());
        }
        
        Framework framework = new Framework(myName.getText(), "enemy", prototype);
       
        return framework;
    }

    /**
     * clones the dialogue box
     */
    public DialogueBox clone() {
        return new EnemyDialogueBox(editorController);
    }
    /**
     * creates framework and adds button when the create enemy button is clicked
     */
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
}



