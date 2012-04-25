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

@SuppressWarnings("serial")
public class EditEnemyDialogue extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    private JTextField myName;
    private JTextField myGroup;
    private Enemy mySprite;
    private AttributeSelectionPanel attributePanel;

    public EditEnemyDialogue(EditorController m, Enemy sprite)
    {
        super(m);
        mySprite = sprite;

        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);

    }

    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
        
        List<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("enemies.movement");
        packagesToSearch.add("attributes");
        attributePanel = new AttributeSelectionPanel(packagesToSearch,
                mySprite.getAttributes(), dialogueController);

        JLabel label1 = new JLabel("Enemy Name:");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName, BorderLayout.SOUTH);

        JLabel groupLabel = new JLabel("Collision Group:");
        
        panel.add(groupLabel);

        myGroup = new JTextField(10);
        String originalGroup = mySprite.getGroup();
        myGroup.setText(originalGroup);

        panel.add(myGroup, BorderLayout.SOUTH);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Edit Enemy";

        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);
        panel.add(attributePanel);

        return panel;
    }

    @Override
    public DialogueBox clone()
    {
        return new EditEnemyDialogue(editorController, mySprite);
    }

    @Override
    protected void BoxCompletedAction()
    {
        ArrayList<Attribute> attributes = attributePanel
                .getSelectedAttributes();
        if (myImagePaths.isEmpty())
        {
            myImagePaths.addAll(mySprite.getImageNames());
        }
        Enemy enemy = new Enemy(0, 0, myImagePaths);
        for (Attribute a : attributes)
        {
            enemy.addAttribute(a);
        }

        enemy.setGroup(myGroup.getText());

        
            editorController.removeSprite(mySprite);
            Framework framework = new Framework(myName.getText(), "enemy",
                    enemy);
            editorController.addFrameworkAndButton(myName.getText(), framework);
            framework.createSprite((int) mySprite.getX(), (int) mySprite.getY());
            

        setVisible(false);

    }

}