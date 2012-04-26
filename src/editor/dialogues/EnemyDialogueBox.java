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


@SuppressWarnings("serial")
public class EnemyDialogueBox extends DialogueBox {

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
        prototype.setGroup(myGroup.getText());
        
        Framework framework = new Framework(myName.getText(), "enemy", prototype);
        System.out.println("framework's attributes" + attributes);
        return framework;
    }


    public DialogueBox clone() {
        return new EnemyDialogueBox(editorController);
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
}



