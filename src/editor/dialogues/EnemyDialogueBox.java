package editor.dialogues;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import editor.AttributeSelectionPanel;
import editor.EditorController;
import editor.frameworks.Framework;
import enemies.Enemy;


import attributes.Attribute;


@SuppressWarnings("serial")
public class EnemyDialogueBox extends DynamicBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    
    private AttributeSelectionPanel attributePanel;
    private JTextField myName;
    private JTextField myGroup;
    


    public EnemyDialogueBox(EditorController m)
    {
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }


    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        List<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("enemies.movement");
        packagesToSearch.add("attributes");
        attributePanel = new AttributeSelectionPanel(packagesToSearch, controller);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
       
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

    public Framework getFramework()
    {
        
        
       /* BufferedImage[] s = new BufferedImage[myImages.size()];
        for (int x = 0; x<s.length; x++)
        {
            s[x] = myImages.get(x);
        }*/
        
        Enemy prototype = new Enemy(0,0, myImagePaths);
        ArrayList<Attribute> attributes = attributePanel.getSelectedAttributes();
        for(Attribute a: attributes)
        {
            prototype.addAttribute(a);
        }
        prototype.setGroup(myGroup.getText());
        
        Framework framework = new Framework(myName.getText(), "enemy", prototype);
        System.out.println("framework's attributes" + attributes);
        return framework;
    }


    public DialogueBox clone() {
        return new EnemyDialogueBox(myController);
    }

    protected void BoxCompletedAction() {
        Framework framework = getFramework();
        myController.addButton(myName.getText(), framework);
        setVisible(false);
        
    }
}



