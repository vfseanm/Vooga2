package editor.exampleStuff;



import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import editor.EditorController;
import editor.Framework;
import editor.dialogues.AttributeSelectionPanel;
import editor.dialogues.DialogueBox;
import enemies.Enemy;


import attributes.Attribute;

/**
 * dialogue box that allows the user to specify how they want to change an entire framework of enemies
 * @author Becky and Sean
 *
 */
@SuppressWarnings("serial")
public class EditEnemyButtonDialogueBox extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    private JTextField myName;
    private JTextField myGroup;
    private Framework myFramework;
    private AttributeSelectionPanel attributePanel;

    /**
     * 
     * @param m level editor controller
     * @param f enemy framework that is being changed
     */
    public EditEnemyButtonDialogueBox(EditorController m, Framework f )
    {
        super(m);
        myFramework = f;
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }
    
    /**
     * creates panel that allows user to edit the information about the enemies
     * created by the framework
     */
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,800));
        Enemy e = (Enemy) myFramework.getPrototype();
        List<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("enemies.movement");
        packagesToSearch.add("attributes");
        attributePanel = new AttributeSelectionPanel(packagesToSearch, e.getAttributes(), dialogueController);
        panel.add(attributePanel);
               
        JLabel label1 = new JLabel("Enemy Name:");
        
        panel.add(label1);

        myName = new JTextField(10);
        String originalName = myFramework.getPrototype().getGroup();
        myName.setText(originalName);

        panel.add(myName, BorderLayout.SOUTH);
        
        JLabel groupLabel = new JLabel("Collision Group:");
        
        panel.add(groupLabel);

        myGroup = new JTextField(10);
        String originalGroup = myFramework.getPrototype().getGroup();
        myGroup.setText(originalGroup);

        panel.add(myGroup, BorderLayout.SOUTH);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Edit Framework";
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;

    }
    /**
     * clones the edit enemy button dialogue box
     */
    @Override
    public DialogueBox clone() {
        return new EditEnemyButtonDialogueBox(editorController, myFramework);
    }
    /**
     * changes framework as specified in the selection panel
     */
    protected void BoxCompletedAction() {
        if(myImagePaths.size()==0)
        {
            myImagePaths = myFramework.getPrototype().getImageNames();
        }

        Enemy newPrototype = new Enemy(0,0, myImagePaths);
        for(Attribute attribute: attributePanel.getSelectedAttributes())
        {
            newPrototype.addAttribute(attribute);
        }
        if(myGroup.getText().length()!=0)
        {
            newPrototype.setGroup(myGroup.getText());
        }
       
        myFramework.updateSprites(newPrototype);
        editorController.closeDialogue();
        
        setVisible(false);
        
    }
    





}