package editor.dialogues;



import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import editor.AttributeSelectionPanel;
import editor.EditorController;
import editor.frameworks.Framework;
import enemies.Enemy;


import attributes.Attribute;


@SuppressWarnings("serial")
public class EditEnemyButtonDialogueBox extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    private JTextField myName;
    private JTextField myGroup;
    private Framework myFramework;
    private AttributeSelectionPanel attributePanel;

    
    public EditEnemyButtonDialogueBox(EditorController m, Framework f )
    {
        super(m);
        myFramework = f;
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }
    

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
               
        JLabel label1 = new JLabel("Enemy Name:");
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

        String buttonPhrase = "Create Enemy";
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);
        panel.add(attributePanel);

        return panel;

    }

    @Override
    public DialogueBox clone() {
        return new EditEnemyButtonDialogueBox(editorController, myFramework);
    }

    protected void BoxCompletedAction() {
        if(myImagePaths.size()==0)
        {
            myImagePaths = myFramework.getPrototype().getImageNames();
        }

        Enemy newPrototype = new Enemy(0,0, myImagePaths);
        newPrototype.setGroup(myFramework.getPrototype().getGroup());
        for(Attribute attribute: attributePanel.getSelectedAttributes())
        {
            newPrototype.addAttribute(attribute);
        }
        if(!(myGroup.getText().equals("")))
        {
            newPrototype.setGroup(myGroup.getText());
        }
       
        myFramework.updateSprites(newPrototype);
        editorController.closeDialogue();
        
        setVisible(false);
        
    }
    





}